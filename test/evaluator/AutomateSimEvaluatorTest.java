package evaluator;

import ast.*;
import model.context.Context;
import model.context.TestContext;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomateSimEvaluatorTest {
    public AutomateSimEvaluator eval;
    public Program program;

    // types
    public static Type lightType;
    public static Type smallLightType;
    public static Type heaterType;

    // rooms
    public static Room bedroom;
    public static Room livingRoom;

    // devices
    public static Device bedroomLight;
    public static Device bedroomLamp;
    public static Device bedroomHeater;

    public static Device mainLight;
    public static Device mainHeater;

    @BeforeEach
    public void setUpAst() {
        /*
         * type Light { enum power [ON, OFF] string color }
         * type SmallLight inherits Light { enum power [DIMMED] }
         * type Heater { enum power [ON, OFF] number level [0, 10] }
         */
        EnumType lightPower = new EnumType(new Var("power"), List.of(new Var("ON"), new Var("OFF")));
        StringType lightColor = new StringType(new Var("color"));

        EnumType smallLightPower = new EnumType(new Var("power"),
                List.of(new Var("ON"), new Var("DIMMED"), new Var("OFF")));

        EnumType heaterPower = new EnumType(new Var("power"), List.of(new Var("ON"), new Var("OFF")));
        NumberType heaterLevel = new NumberType(new Var("level"), 0, 10);

        lightType = new Type(new Var("Light"), null, List.of(lightPower, lightColor));
        smallLightType = new Type(new Var("SmallLight"), lightType, List.of(smallLightPower, lightColor));
        heaterType = new Type(new Var("Heater"), null, List.of(heaterPower, heaterLevel));

        /*
         * room bedroom {
         *      bedroom_light of Light(OFF, "ffffff")
         *      bedroom_lamp of SmallLight(OFF, "ffebd9")
         *      bedroom_heater of Heater(OFF, 3)
         *  }
         *
         *  room living_room {
         *      main_light of Light(OFF, "ffffff")
         *      main_heater of Heater(OFF, 3)
         *  }
         */
        EnumVal bedroomLightPower = new EnumVal("power", new Var("OFF"), lightPower);
        StringVal bedroomLightColor = new StringVal("color", "ffffff", lightColor);
        bedroomLight = new Device(new Var("bedroom_light"), lightType,
                List.of(bedroomLightPower, bedroomLightColor));

        EnumVal bedroomLampPower = new EnumVal("power", new Var("OFF"), smallLightPower);
        StringVal bedroomLampColor = new StringVal("color", "ffebd9", lightColor);
        bedroomLamp = new Device(new Var("bedroom_lamp"), smallLightType,
                List.of(bedroomLampPower, bedroomLampColor));

        EnumVal bedroomHeaterPower = new EnumVal("power", new Var("OFF"), heaterPower);
        NumberVal bedroomHeaterLevel = new NumberVal("level", "3", heaterLevel);
        bedroomHeater = new Device(new Var("bedroom_heater"), heaterType,
                List.of(bedroomHeaterPower, bedroomHeaterLevel));

        EnumVal mainLightPower = new EnumVal("power", new Var("OFF"), lightPower);
        StringVal mainLightColor = new StringVal("level", "ffffff", lightColor);
        mainLight = new Device(new Var("main_light"), lightType,
                List.of(mainLightPower, mainLightColor));

        EnumVal mainHeaterPower = new EnumVal("power", new Var("OFF"), heaterPower);
        NumberVal mainHeaterLevel = new NumberVal("level", "3", heaterLevel);
        mainHeater = new Device(new Var("main_heater"), heaterType,
                List.of(mainHeaterPower, mainHeaterLevel));

        bedroom = new Room(new Var("bedroom"), List.of(bedroomLight, bedroomLamp, bedroomHeater));
        livingRoom = new Room(new Var("living_room"), List.of(mainLight, mainHeater));
    }

    @BeforeEach
    void setUp() {
        Context.clear();
        TestContext.clear();

        eval = new AutomateSimEvaluator();
    }

    @Test
    void testTrivial() {
        /* no rooms or actions, just one type */
        program = new Program(List.of(lightType));
        eval.visit(null, program);

        assertEquals(0, TestContext.getRooms().size());
        assertEquals(0, TestContext.getActions().size());
    }

    @Test
    void testSimpleAction() {
        /*
         * one simple action:
         * action turn_on_bedroom_light on main_light.power {
         *     set bedroom_light.power to ON
         * }
         */
        SetStatement set = new SetStatement(new DeviceProp(new Var("bedroom_light"), new Var("power")),
                new EnumVal("power", new Var("ON"), bedroomLight.getType().getProperties().getFirst()));

        Action action = new Action(new Var("turn_on_bedroom_light"),
                List.of(new DeviceProp(new Var("main_light"), new Var("power"))), List.of(set));

        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom, action));
        eval.visit(null, program);

        assertEquals(2, TestContext.getRooms().size());
        assertEquals(1, TestContext.getActions().size());
        assertEquals(5, Context.getDevices().size());

        model.Device modelMainLight = Context.getDevice("main_light");
        assertNotNull(modelMainLight);

        model.Property lightPowerProp = modelMainLight.getProp("power");
        assertNotNull(lightPowerProp);

        lightPowerProp.mutate("ON");

        List<String> prints = TestContext.getPrints();
        assertEquals(1, prints.size());
        assertEquals("power of bedroom_light has been changed to ON.", prints.getFirst());
    }

    @Test
    void testMultiplePropagatingActions() {
        /*
         * two simple actions, where one triggers the other:
         * action turn_on_bedroom_light on main_light.power {
         *     set bedroom_light.power to ON
         * }
         *
         * action turn_up_heater on bedroom_light.power {
         *     set bedroom_heater.level to 8
         * }
         */

        SetStatement set1 = new SetStatement(new DeviceProp(new Var("bedroom_light"), new Var("power")),
                new EnumVal("power", new Var("ON"), bedroomLight.getType().getProperties().getFirst()));

        Action action1 = new Action(new Var("turn_on_bedroom_light"),
                List.of(new DeviceProp(new Var("main_light"), new Var("power"))), List.of(set1));

        SetStatement set2 = new SetStatement(new DeviceProp(new Var("bedroom_heater"), new Var("level")),
                new NumberVal("level", "8", bedroomHeater.getType().getProperties().get(1)));

        Action action2 = new Action(new Var("turn_up_heater"),
                List.of(new DeviceProp(new Var("bedroom_light"), new Var("power"))), List.of(set2));

        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom, action1, action2));
        eval.visit(null, program);

        assertEquals(2, TestContext.getRooms().size());
        assertEquals(2, TestContext.getActions().size());
        assertEquals(5, Context.getDevices().size());

        model.Device modelMainLight = Context.getDevice("main_light");
        assertNotNull(modelMainLight);

        model.Property lightPowerProp = modelMainLight.getProp("power");
        assertNotNull(lightPowerProp);

        lightPowerProp.mutate("ON");

        List<String> prints = TestContext.getPrints();
        assertEquals(2, prints.size());
        assertEquals("level of bedroom_heater has been changed to 8.", prints.get(0));
        assertEquals("power of bedroom_light has been changed to ON.", prints.get(1));
    }

    @Test
    void testIfStmtAction() {
        /*
         * action with if-condition:
         * action turn_on_bedroom_light on main_light.power {
         *     if main_light.power is OFF {
         *        set bedroom_light.power to ON
         *     }
         * }
         */

        SetStatement set1 = new SetStatement(new DeviceProp(new Var("bedroom_light"), new Var("power")),
                new EnumVal("power", new Var("ON"), bedroomLight.getType().getProperties().getFirst()));

        IfStatement if1 = new IfStatement(new DeviceProp(new Var("main_light"), new Var("power")),
                new EnumVal("power", new Var("OFF"), mainLight.getType().getProperties().getFirst()),
                List.of(set1));

        Action action1 = new Action(new Var("turn_on_bedroom_light"),
                List.of(new DeviceProp(new Var("main_light"), new Var("power"))), List.of(if1));

        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom, action1));
        eval.visit(null, program);

        // asserts
        assertEquals(2, TestContext.getRooms().size());
        assertEquals(1, TestContext.getActions().size());
        assertEquals(5, Context.getDevices().size());

        model.Device modelMainLight = Context.getDevice("main_light");
        assertNotNull(modelMainLight);

        model.Property lightPowerProp = modelMainLight.getProp("power");
        assertNotNull(lightPowerProp);

        lightPowerProp.mutate("ON");

        List<String> prints = TestContext.getPrints();
        assertEquals(0, prints.size());

        lightPowerProp.mutate("OFF");

        assertEquals(1, prints.size());
        assertEquals("power of bedroom_light has been changed to ON.", prints.get(0));
    }

    @Test
    void testForStmtAction() {
        /*
         * action with for-loop:
         * action turn_on_bedroom_light on main_light.power { // this should turn off all Type light objects and it's children!
         *     if main_light.power is OFF {
         *        set bedroom_light.power to ON
         *     }
         * }
         */

        SetStatement set1 = new SetStatement(new DeviceProp(new Var("Light"), new Var("power")),
                new EnumVal("power", new Var("ON"), bedroomLight.getType().getProperties().getFirst()));

        ForStatement for1 = new ForStatement(new Var("turn_off_all_bedroom_lights"), lightType, bedroom, List.of(set1));

        Action action1 = new Action(new Var("turn_on_bedroom_light"),
                List.of(new DeviceProp(new Var("main_light"), new Var("power"))), List.of(for1));

        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom, action1));
        eval.visit(null, program);

        // asserts
        assertEquals(2, TestContext.getRooms().size());
        assertEquals(1, TestContext.getActions().size());
        assertEquals(5, Context.getDevices().size());

        // checking bedroom_light to make sure it's initially OFF
        model.Device modelBedroomLight = Context.getDevice("bedroom_light");
        assertNotNull(modelBedroomLight);

        model.Property bedroomLightPowerProp = modelBedroomLight.getProp("power");
        assertNotNull(bedroomLightPowerProp);
        assertEquals("OFF", bedroomLightPowerProp.getValue());

        // checking bedroom_lamp to make sure it's initially OFF
        model.Device modelBedroomLamp = Context.getDevice("bedroom_lamp");
        assertNotNull(modelBedroomLamp);

        model.Property bedroomLampPowerProp = modelBedroomLamp.getProp("power");
        assertNotNull(bedroomLampPowerProp);
        assertEquals("OFF", bedroomLampPowerProp.getValue());

        // triggering the action
        model.Device modelMainLight = Context.getDevice("main_light");
        assertNotNull(modelMainLight);

        model.Property lightPowerProp = modelMainLight.getProp("power");
        assertNotNull(lightPowerProp);

        lightPowerProp.mutate("ON");

        List<String> prints = TestContext.getPrints();
        assertEquals(2, prints.size());
        assertEquals("power of bedroom_light has been changed to ON.", prints.get(0));
        assertEquals("power of bedroom_lamp has been changed to ON.", prints.get(1));
    }

    @Test
    void testInheritance() {
        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom));
        eval.visit(null, program);

        model.Device modelBedroomLamp = Context.getDevice("bedroom_lamp");
        assertNotNull(modelBedroomLamp);

        model.Property bedroomLampPowerProp = modelBedroomLamp.getProp("power");
        assertNotNull(bedroomLampPowerProp);
        assertEquals("OFF", bedroomLampPowerProp.getValue());

        bedroomLampPowerProp.mutate("DIMMED");

        assertNotNull(bedroomLampPowerProp);
        assertEquals("DIMMED", bedroomLampPowerProp.getValue());
    }

    @Test
    void testFull() {
        /*
         * full set of actions representing a typical program
         *
         * action turn_off_heater on main_light.power {
         *     set bedroom_light.power to ON
         * }
         *
         * action set_all_bedroom_lights_off_if_color_is_ffffff on bedroom_light.power {
         *     for color of Light in bedroom {
         *         if Light.color is "ffffff" {
         *            set Light.power to ON
         *         }
         *     }
         * }
         */
        SetStatement set1 = new SetStatement(new DeviceProp(new Var("Light"), new Var("power")),
                new EnumVal("power", new Var("ON"), bedroomLight.getType().getProperties().getFirst()));

        IfStatement if1 = new IfStatement(new DeviceProp(new Var("Light"), new Var("color")),
                new StringVal("color", "ffffff", mainLight.getType().getProperties().getFirst()),
                List.of(set1));

        ForStatement for1 = new ForStatement(new Var("turn_off_all_bedroom_lights"), lightType, bedroom, List.of(if1));

        Action action1 = new Action(new Var("set_all_bedroom_lights_off_if_color_is_ffffff"),
                List.of(new DeviceProp(new Var("bedroom_heater"), new Var("level"))), List.of(for1));


        SetStatement set2 = new SetStatement(new DeviceProp(new Var("bedroom_heater"), new Var("level")),
                new NumberVal("level", "0", bedroomLight.getType().getProperties().getFirst()));

        Action action2 = new Action(new Var("turn_off_heater"),
                List.of(new DeviceProp(new Var("main_light"), new Var("power"))), List.of(set2));

        program = new Program(List.of(lightType, smallLightType, heaterType, bedroom, livingRoom, action1, action2));
        eval.visit(null, program);

        assertEquals(2, TestContext.getRooms().size());
        assertEquals(2, TestContext.getActions().size());
        assertEquals(5, Context.getDevices().size());

        model.Device modelMainLight = Context.getDevice("main_light");
        assertNotNull(modelMainLight);

        model.Property lightPowerProp = modelMainLight.getProp("power");
        assertNotNull(lightPowerProp);

        lightPowerProp.mutate("ON");

        List<String> prints = TestContext.getPrints();
        assertEquals(2, prints.size());
        assertEquals("power of bedroom_light has been changed to ON.", prints.get(0));
        assertEquals("level of bedroom_heater has been changed to 0.", prints.get(1));
        TestContext.clear();

        model.Device bedroomLamp = Context.getDevice("bedroom_lamp");
        assertNotNull(bedroomLamp);

        bedroomLamp.getProp("color").mutate("ffffff");
        lightPowerProp.mutate("ON");

        assertEquals(3, prints.size());
        assertEquals("power of bedroom_light has been changed to ON.", prints.get(0));
        assertEquals("power of bedroom_lamp has been changed to ON.", prints.get(1));
        assertEquals("level of bedroom_heater has been changed to 0.", prints.get(2));
    }
}

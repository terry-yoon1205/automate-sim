package ui;

import ast.*;
import evaluator.AutomateSimEvaluator;

import java.util.List;

public class Main {
    // to be changed. below has been pasted from tests
    public static AutomateSimEvaluator eval;
    public static Program program;

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

    public static void main(String[] args) {
        setUpAst();
        setUpProgram();

        AutomateSim sim = new AutomateSim();
        sim.run();
    }

    private static void setUpAst() {
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

    private static void setUpProgram() {
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

        eval = new AutomateSimEvaluator();
        eval.visit(null, program);
    }
}

package evaluator;

import ast.*;
import model.context.Context;
import model.context.TestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AutomateSim;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    @BeforeAll
    static void setUpAst() {
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
        StringVal bedroomLightColor = new StringVal("level", "ffffff", lightColor);
        bedroomLight = new Device(new Var("bedroom_light"), lightType,
                List.of(bedroomLightPower, bedroomLightColor));

        EnumVal bedroomLampPower = new EnumVal("power", new Var("OFF"), smallLightPower);
        StringVal bedroomLampColor = new StringVal("level", "ffebd9", lightColor);
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
        bedroomHeater = new Device(new Var("bedroom_heater"), heaterType,
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
        // no rooms or actions, just one type
        program = new Program(List.of(lightType));
        eval.visit(null, program);
    }

    @Test
    void testSimpleAction() {
        // one simple action
    }

    @Test
    void testMultiplePropagatingActions() {
        // two actions, one trigger the other
    }

    @Test
    void testIfStmtAction() {

    }

    @Test
    void testForStmtAction() {

    }

    @Test
    void testFull() {

    }

    @Test
    void UiChangeDevicePropertyTest() {
        // =============================================================================================================
        // setup electronicType supertype.
        // =============================================================================================================
        Var electronicPower = new Var("ELECTRONIC_POWER");

        EnumType electronicPropType = new EnumType(electronicPower, List.of(new Var("ON"), new Var("OFF")));

        Type electronicType = new Type(new Var("electronicType"), null, List.of(electronicPropType));

        // =============================================================================================================
        // setup windowType and declare a window device
        // =============================================================================================================


        Var windowStatus = new Var("WINDOW_STATUS");

        EnumType windowPropType = new EnumType(windowStatus, List.of(new Var("OPEN"), new Var("CLOSED")));

        Type windowType = new Type(new Var("windowType"), null, List.of(windowPropType));

        List<PropVal> windowInitialState = List.of(new EnumVal(windowStatus.getText(), new Var("OPEN"), windowPropType));

        Var windowVar = new Var("Window");
        Device window = new Device(windowVar, windowType, windowInitialState);

        // =============================================================================================================
        // setup tvType and declare TV device
        // =============================================================================================================
        Var volVar = new Var("VOLUME");
        Var disVar = new Var("DISPLAY_TEXT");
        NumberType vol = new NumberType(volVar, 0, 100);
        StringType dis = new StringType(disVar);


        Type tvType = new Type(new Var("tvType"), electronicType, List.of(vol, dis, electronicPropType));

        List<PropVal> tvInitialStates = List.of(
                new EnumVal(electronicPower.getText(), new Var("OFF"), electronicPropType),
                new NumberVal(volVar.getText(), "50", vol),
                new StringVal(disVar.getText(), "DISPLAY TEST!!!", dis));

        // Do not declare "POWER" enum because it inherits it from electronicType.

        Var tvVar = new Var("Tv");
        Device tv = new Device(tvVar, tvType, tvInitialStates);

        // =============================================================================================================
        // setup the room.
        // =============================================================================================================
        Room bedroom = new Room(new Var("Bedroom"), List.of(window, tv));

        // =============================================================================================================
        // setup some statements for the tv.
        // =============================================================================================================
        SetStatement setTvOn = new SetStatement(new DeviceProp(tvVar, electronicPower),
                new EnumVal(electronicPower.getText(),
                        new Var("ON"), electronicPropType));


        SetStatement increaseTvVolume = new SetStatement(new DeviceProp(tvVar, volVar),
                new NumberVal(volVar.getText(), "90", vol));


        // =============================================================================================================
        // setup the if statement that will trigger the previous set statements for tv.
        // =============================================================================================================
        IfStatement ifWindowClosesSetTvOn = new IfStatement(new DeviceProp(windowVar, windowStatus),
                new EnumVal(windowStatus.getText(), new Var("CLOSED"), windowPropType), List.of(setTvOn, increaseTvVolume));

        // =============================================================================================================
        // setup the Action.
        // =============================================================================================================

        Action turnUpTvIfWindowCloses = new Action(new Var("Turn up TV if window closes"),
                List.of(new DeviceProp(windowVar, windowStatus)),
                List.of(ifWindowClosesSetTvOn));




        // =============================================================================================================
        // Start the program and populate the Model.Context for testing.
        // =============================================================================================================

        List<Decl> decls = new ArrayList<>();

        decls.add(electronicType);
        decls.add(windowType);
        decls.add(tvType);
        decls.add(bedroom);
        decls.add(turnUpTvIfWindowCloses);

        Program program = new Program(decls);
        eval.visit(null, program);

        HashMap<String, Set<String>> rooms =  TestContext.getRooms();
        HashMap<String, model.Device> devices = Context.getDevices();
        HashMap<String, model.Action> actions = TestContext.getActions();

        String testInput = "Tv.ELECTRONIC_POWER\n" +
                "OFF\n" +
                "!q";

        ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        AutomateSim ui = new AutomateSim(in, out);
        ui.run();

        // Get the bytes written to the ByteArrayOutputStream
        byte[] outputBytes = out.toByteArray();

        // Convert the bytes to a string and print it
        String outputString = new String(outputBytes);
        System.out.println("Output from PrintStream: \n" + outputString);
    }
}

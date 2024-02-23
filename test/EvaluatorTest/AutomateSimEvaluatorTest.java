package EvaluatorTest;

import ast.*;
import evaluator.TestEvaluator;
import model.context.Context;
import model.context.TestContext;
import org.junit.jupiter.api.Test;
import ui.AutomateSim;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AutomateSimEvaluatorTest {
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

        TestEvaluator evaluator = new TestEvaluator();
        StringBuilder stringBuilder = new StringBuilder();
        evaluator.visit(stringBuilder, program);

        String output = stringBuilder.toString();

        System.out.println(output);

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

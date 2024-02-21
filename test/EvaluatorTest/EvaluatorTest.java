package EvaluatorTest;

import ast.*;
import evaluator.TestEvaluator;
import model.context.Context;

import model.IfStmt;
import model.SetStmt;

import model.context.TestContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    @Test
    void basicRoomDecl() {
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


        // room checks
        assertEquals(1, rooms.size());
        assertNotNull(rooms.get("Bedroom"));

        // device checks

        // window
        model.Device windowTest = devices.get(windowVar.getText());

        assertNotNull(windowTest);
        assertSame(windowTest.getName(), windowVar.getText());

        assertNotNull(windowTest.getProp(windowStatus.getText()));

        assertSame("OPEN", windowTest.getProp(windowStatus.getText()).getValue());


        // tv
        model.Device tvTest = devices.get(tvVar.getText());

        assertNotNull(tvTest);
        assertSame(tvTest.getName(), tvVar.getText());

        assertNotNull(tvTest.getProp(volVar.getText()));
        assertNotNull(tvTest.getProp(disVar.getText()));
        assertNotNull(tvTest.getProp(electronicPower.getText()));

        assertSame("50", tvTest.getProp(volVar.getText()).getValue());
        assertSame("DISPLAY TEST!!!", tvTest.getProp(disVar.getText()).getValue());
        assertSame("OFF", tvTest.getProp(electronicPower.getText()).getValue());



        // action tests

        assertEquals(1, actions.size());

        model.Action actionTest = actions.get(turnUpTvIfWindowCloses.getName().getText());

        assertNotNull(actionTest);

        assertEquals(3, actionTest.getStmts().size());

        SetStmt tvElecPower = (SetStmt) actionTest.getStmts().get(0);
        SetStmt tvVol       = (SetStmt) actionTest.getStmts().get(1);
        IfStmt windowClose  = (IfStmt) actionTest.getStmts().get(2);

        assertNotNull(tvElecPower);
        assertEquals(electronicPower.getText(), tvElecPower.getVal());

        assertNotNull(tvVol);
        assertEquals(volVar.getText(), tvVol.getVal());

        assertNotNull(windowClose);
        assertEquals("CLOSED", windowClose.getVal());


//        assertTrue(output.contains(electronicType.getName().getText()));
//        assertTrue(output.contains(lampOff.getText()));
//        assertTrue(output.contains(windowStatus.getText()));
//        assertTrue(output.contains(windowVar.getText()));
//        assertTrue(output.contains(tvType.getName().getText()));
//        assertTrue(output.contains(tv.getName().getText()));
//        assertTrue(output.contains(bedroom.getName().getText()));
//        assertTrue(output.contains(turnOnTv.getName().getText()));
    }
}

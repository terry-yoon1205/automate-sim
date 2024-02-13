package EvaluatorTest;

import ast.*;
import evaluator.Evaluator;
import model.Context;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluatorTest {
    @Test
    void basicRoomDecl() {
        // setup electronicType supertype.
        Var electronicPower = new Var("ELECTRONIC_POWER");
        PropType electronicPropType = new EnumType(electronicPower, List.of(new Var("ON"), new Var("OFF")));
        List<PropType> electronicProperties = List.of(electronicPropType);
        Type electronicType = new Type(new Var("electronicType"), null, electronicProperties);

        // setup lampType and declare a lamp device
        List<PropVal> lampPowerOn = List.of(new EnumVal("lampVar", new Var("ON"), electronicPropType));

        Var lampOff = new Var("OFF");

        Var lampPower = new Var("LAMP_POWER");
        List<PropType> lampProperties = List.of(new EnumType(lampPower, List.of(new Var("ON"), lampOff)));
        Type lampType = new Type(new Var("lampType"), null, lampProperties);

        Var lampVar = new Var("lamp");
        Device lamp = new Device(lampVar, lampType, lampPowerOn);

        // setup tvType and declare TV device
        NumberType vol = new NumberType(new Var("VOLUME"), 0, 100);
        StringType str = new StringType(new Var("DISPLAY_TEXT"));
        List<PropType> tvProperties = List.of(vol, str);

        Type tvType = new Type(new Var("tvType"), electronicType, tvProperties);
        List<PropVal> tvVal = List.of(
                new EnumVal("tvVar", new Var("OFF"), electronicPropType),
                new NumberVal("volVar", "50", vol), new StringVal("displayVar",
                        "DISPLAY TEST!!!", str));

        // Do not declare "POWER" enum because it inherits it from electronicType.

        Var tvVar = new Var("tv");
        Device tv = new Device(tvVar, tvType, tvVal);

        // setup the room.
        Room bedroom = new Room(new Var("Bedroom"), List.of(lamp, tv));

        // ============================================================================================================= //

        // setup some statements for the tv.
        SetStatement setTvOn = new SetStatement(new DeviceProp(tvVar, electronicPower), new EnumVal("tvVar", new Var("ON"), electronicPropType));


        // setup the if statement that will trigger the previous set statements for tv.
        IfStatement ifLampTurnsOffSetTvOn = new IfStatement(new DeviceProp(lampVar, lampPower),
                new EnumVal("tvVar", new Var("OFF"), electronicPropType), List.of(setTvOn));

        // setup the Action.
        Action turnOnTv = new Action(new Var("Turn on TV"), List.of(new DeviceProp(lampVar, lampPower)), List.of(ifLampTurnsOffSetTvOn));

        // ============================================================================================================= //

        List<Decl> decls = new ArrayList<>();

        decls.add(electronicType);
        decls.add(lampType);
        decls.add(tvType);
        decls.add(bedroom);
        decls.add(turnOnTv);

        Program program = new Program(decls);

        Evaluator evaluator = new Evaluator();
        StringBuilder stringBuilder = new StringBuilder();
        evaluator.visit(stringBuilder, program);

        String output = stringBuilder.toString();

        System.out.println(output);

        HashMap<String, Set<String>> temp1 =  Context.getRooms();
        HashMap<String, model.Device> temp2 = Context.getDevices();

        assertTrue(output.contains(electronicPower.getText()));
        assertTrue(output.contains(electronicType.getName().getText()));
        assertTrue(output.contains(lampOff.getText()));
        assertTrue(output.contains(lampPower.getText()));
        assertTrue(output.contains(lampVar.getText()));
        assertTrue(output.contains(tvType.getName().getText()));
        assertTrue(output.contains(tv.getName().getText()));
        assertTrue(output.contains(bedroom.getName().getText()));
        assertTrue(output.contains(turnOnTv.getName().getText()));
    }
}

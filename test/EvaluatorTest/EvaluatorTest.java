package EvaluatorTest;

import ast.*;
import evaluator.Evaluator;
import org.junit.jupiter.api.Test;

import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EvaluatorTest {

    @Test
    void basicRoomDecl() {



        // setup electronicType supertype.
        Var electronicPower = new Var("ELECTRONIC_POWER");
        List<PropType> electronicProperties = List.of(new EnumType(electronicPower, List.of(new Var("ON"), new Var("OFF"))));
        Type electronicType = new Type(new Var("electronicType"), null, electronicProperties);

        // setup lampType and declare a lamp device
        List<PropVal> lampPowerOn = List.of(new EnumVal(new Var("ON")));

        Var lampOff = new Var("OFF");

        Var lampPower = new Var("LAMP_POWER");
        List<PropType> lampProperties = List.of(new EnumType(lampPower, List.of(new Var("ON"), lampOff)));
        Type lampType = new Type(new Var("lampType"), null, lampProperties);

        Var lampVar = new Var("lamp");
        Device lamp = new Device(lampVar, lampType, lampPowerOn);


        // setup tvType and declare TV device
        List<PropVal> tvVal = List.of(new EnumVal(new Var("OFF")), new NumberVal(50), new StringVal("DISPLAY TEST!!!"));

        // Do not declare "POWER" enum because it inherits it from electronicType.
        List<PropType> tvProperties = List.of(new NumberType(new Var("VOLUME"), 0, 100),
                                              new StringType(new Var("DISPLAY_TEXT")));

        Type tvType = new Type(new Var("tvType"), electronicType, tvProperties);
        Var tvVar = new Var("tv");
        Device tv = new Device(tvVar, tvType, tvVal);


        // setup the room.
        Room bedroom = new Room(new Var("Bedroom"), List.of(lamp, tv));



        // ============================================================================================================= //

        // setup some statements for the tv.
        SetStatement setTvOn = new SetStatement(new DeviceProp(tvVar, electronicPower), new EnumVal(new Var("ON")));


        // setup the if statement that will trigger the previous set statements for tv.
        IfStatement ifLampTurnsOffSetTvOn = new IfStatement(new DeviceProp(lampVar, lampPower),
                                                            new EnumVal(new Var("OFF")), List.of(setTvOn));


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


        System.out.println(stringBuilder);






    }


}

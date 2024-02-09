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
    void basicDeviceDecl() {

        List<PropType> lampProperties = new ArrayList<PropType>();
        lampProperties.add(new EnumType(new Var("POWER"), new ArrayList<Var>(new Var("ON"), new Var("OFF"))));

        List<Decl> decls = new ArrayList<>();
        Program program = new Program(decls);


        Var lampVar = new Var("lamo");

        Evaluator evaluator = new Evaluator();
        StringBuilder stringBuilder = new StringBuilder();
        evaluator.visit(stringBuilder, program);

//        Type lampType = new Type(lampVar, null, new List<PropType>() {
//        })
//        Device device1 = new Device()
    }


}

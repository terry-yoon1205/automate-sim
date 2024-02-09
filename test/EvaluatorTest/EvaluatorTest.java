package EvaluatorTest;

import ast.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EvaluatorTest {

    @Test
    void basicDeviceDecl() {

        List<PropType> lampProperties = new ArrayList<PropType>();
        lampProperties.add(new EnumType(new Var("POWER"), new ArrayList<Var>(new Var("ON"), new Var("OFF"))));

        Var lampVar = new Var("lamo");
        Type lampType = new Type(lampVar, null, new List<PropType>() {
        })
        Device device1 = new Device()
    }


}

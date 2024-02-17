package parser;

import ast.*;

import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParserTest {
    static ParseTreeToAST visitor;

    @BeforeAll
    public static void initialization() {
        visitor = new ParseTreeToAST();
    }

    @Test
    void ActionTest() {
        String legalInput = "action sync_heaters on [main_heater.level, bedroom_heater.level] {\nset bedroom_heater.level to main_heater.level\n}";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ActionContext a = parser.action();
        Node testAction = a.accept(visitor);
        assertEquals(Action.class, testAction.getClass());
        assertEquals(new Var("sync_heaters"), ((Action) testAction).getName());
        assertEquals(2, ((Action) testAction).getTriggers().size());
        assertEquals(1, ((Action) testAction).getStatements().size());
    }

    @Test
    void ComplexActionTest() {
        String legalInput = "action bedroom_all on bedroom_switch.state {\n" +
                "    if bedroom_switch.state is ON {\n" +
                "        set bedroom_light.power to ON\n" +
                "        set bedroom_lamp.power to DIMMED\n" +
                "        set bedroom_heater.power to ON\n" +
                "    }\n" +
                "}";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ActionContext a = parser.action();
        Node testAction = a.accept(visitor);
        assertEquals(Action.class, testAction.getClass());
        assertEquals(new Var("bedroom_all"), ((Action) testAction).getName());
        assertEquals(1, ((Action) testAction).getTriggers().size());
        assertEquals(1, ((Action) testAction).getStatements().size());
    }

    @Test
    void DeviceTest() {
        String legalInput = "main_light of Light(OFF, \"ffffff\")";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.DeviceContext d = parser.device();
        Node testDevice = d.accept(visitor);
        assertEquals(Device.class, testDevice.getClass());
        assertEquals(new Var("main_light"), ((Device) testDevice).getName());
//            assertEquals(new Type("Switch"), ((Device) testDevice).getName());
        assertEquals(2, ((Device) testDevice).getValues().size());
    }

    @Test
    void EnumTypeTest() {
        String legalInput = "enum power [ON, OFF]";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.EnumContext e = parser.enum_();
        Node testEnumType = e.accept(visitor);
        assertEquals(((EnumType)testEnumType).getName(), new Var("power"));
        assertEquals(((EnumType)testEnumType).getStates(), List.of(new Var("ON"), new Var("OFF")));
    }

    @Test
    void EnumTypeErrorTest() {
        String legalInput = "enum power []";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.EnumContext e = parser.enum_();
        Node testEnumType = e.accept(visitor);
        assertNull(testEnumType);
    }

    @Test
    void IfStatementTest() {
        String legalInput = "if bedroom_switch.state is ON {\n" +
                    "        set bedroom_light.power to ON\n" +
                    "        set bedroom_lamp.power to DIMMED\n" +
                    "        set bedroom_heater.power to ON\n" +
                    "    }";

        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.IfContext e = parser.if_();
        Node testIfStmt = e.accept(visitor);
        assertEquals("bedroom_switch", ((IfStatement) testIfStmt).getDeviceProp().getDevice().getText());
        assertEquals("state", ((IfStatement) testIfStmt).getDeviceProp().getProp().getText());

        PropVal actual = ((IfStatement) testIfStmt).getValue();
        assertEquals("state", actual.getVarName());
        assertEquals("ON", actual.getValue());
        EnumType actualType = ((EnumType) actual.getType());
        assertEquals(new Var("state"),actualType.getName());
        assertEquals(List.of(new Var("ON"), new Var("OFF")), actualType.getStates());
        assertEquals(3, ((IfStatement) testIfStmt).getStatements().size());
    }

    @Test
    void IfEmptyStatementTest() {
        String legalInput = "if bedroom_switch.state is ON {\n" +
                "    }";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.IfContext e = parser.if_();
        Node testIfStmt = e.accept(visitor);
        PropVal actual = ((IfStatement) testIfStmt).getValue();
        assertEquals("state", actual.getVarName());
        assertEquals("ON", actual.getValue());
        EnumType actualType = ((EnumType) actual.getType());
        assertEquals(new Var("state"),actualType.getName());
        assertEquals(List.of(new Var("ON"), new Var("OFF")), actualType.getStates());
        assertEquals(0, ((IfStatement) testIfStmt).getStatements().size());
    }
    @Test
    void numberTypeLegalTest() {
        String legalInput = "number level [0, 100]";
        AutomateSimParser parser = constructParser(legalInput);


        String noMin = "number level [, 10]";
        AutomateSimParser.NumberContext n = parser.number();
        Node testNumberType = n.accept(visitor);
        assertEquals(new Var("level"), ((NumberType)testNumberType).getName());
        assertEquals(0, ((NumberType)testNumberType).getMin());
        assertEquals(100, ((NumberType)testNumberType).getMax());
    }

    @Test
    void numberTypeErrorTest() {
        String[] errorTestStrings = {"number level [100, ]" , "number level [0, 9.9]", "number level [100, 2]"};
//            String unused = "number level [ , 10]";
        for (String s : errorTestStrings) {
            AutomateSimParser parser = constructParser(s);

            AutomateSimParser.NumberContext n = parser.number();
            Node testNumberType = n.accept(visitor);
            assertNull(testNumberType);
        }

    }

    private static AutomateSimParser constructParser(String legalInput) {
        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        return new AutomateSimParser(tokens);
    }
}



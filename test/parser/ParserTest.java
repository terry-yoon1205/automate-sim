package parser;

import ast.*;

import java.util.List;

import exceptions.ParserException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    static ParseTreeToAST visitor;

    @BeforeEach
    public void initialization() {
        visitor = new ParseTreeToAST();
    }

    @Test
    void ActionTest() {
        String legalInput = """
                type Switch {
                    enum state [ON, OFF]
                }
                type Light {
                    enum power [ON, OFF]
                }
                type DimmableLight inherits Light {
                    enum power [DIMMED]
                }
                type Heater {
                    number level [0, 10]
                    enum power [ON, OFF]
                }
                
                room Bedroom {
                    bedroom_switch of Switch(OFF)
                    bedroom_light of Light(OFF)
                    bedroom_lamp of DimmableLight(OFF)
                    bedroom_heater of Heater(0, OFF)
                }
                room Main {
                    main_heater of Heater(0, OFF)
                }
                action sync_heaters on [main_heater.level, bedroom_heater.level] {
                    set bedroom_heater.level to main_heater.level
                }
                """;
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ProgramContext p = parser.program();
        Program testProgram = (Program) p.accept(visitor);

        Node testAction = testProgram.getStatements().get(6);
        assertEquals(Action.class, testAction.getClass());
        assertEquals(new Var("sync_heaters"), ((Action) testAction).getName());
        assertEquals(2, ((Action) testAction).getTriggers().size());
        assertEquals(1, ((Action) testAction).getStatements().size());
    }

    @Test
    void ComplexActionTest() {
        String legalInput = """
                type Switch {
                    enum state [ON, OFF]
                }
                type Light {
                    enum power [ON, OFF]
                }
                type DimmableLight inherits Light {
                    enum power [DIMMED]
                }
                type Heater {
                    enum power [ON, OFF]
                }
                room Bedroom {
                    bedroom_switch of Switch(OFF)
                    bedroom_light of Light(OFF)
                    bedroom_lamp of DimmableLight(OFF)
                    bedroom_heater of Heater(OFF)
                }
                action bedroom_all on bedroom_switch.state {
                    if bedroom_switch.state is ON {
                        set bedroom_light.power to ON
                        set bedroom_lamp.power to DIMMED
                        set bedroom_heater.power to ON
                    }
                }""";
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ProgramContext a = parser.program();
        Program testProgram = (Program) a.accept(visitor);
        assertEquals(6, testProgram.getStatements().size());
        Action testAction = (Action) testProgram.getStatements().get(5);
        assertEquals(Action.class, testAction.getClass());
        assertEquals(new Var("bedroom_all"), ((Action) testAction).getName());
        assertEquals(1, ((Action) testAction).getTriggers().size());
        assertEquals(1, ((Action) testAction).getStatements().size());
    }

    @Test
    void DeviceTest() {
        EnumType mockEnum = new EnumType(new Var("State"), List.of(new Var("ON"), new Var("OFF")));
        StringType mockStringType = new StringType(new Var("color"));
        Type mockType = new Type(new Var("Light"), null, List.of(mockEnum, mockStringType));
        visitor.addedType.add(mockType);
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
        try {
            AutomateSimParser.EnumContext e = parser.enum_();
            Node testEnumType = e.accept(visitor);
            fail("Expected exception");
        } catch (ParserException e) {
            //pass
        }
    }

    @Test
    void IfStatementTest() {
        String legalInput =
                """
                type Switch {
                    enum state [ON, OFF]
                }
                type Light {
                    enum power [ON, OFF]
                }
                type DimmableLight inherits Light {
                    enum power [DIMMED]
                }
                type Heater {
                    enum power [ON, OFF]
                }
                room Bedroom {
                    bedroom_switch of Switch(OFF)
                    bedroom_light of Light(OFF)
                    bedroom_lamp of DimmableLight(OFF)
                    bedroom_heater of Heater(OFF)
                }
                action bedroom_all on bedroom_switch.state {
                    if bedroom_switch.state is ON {
                            set bedroom_light.power to ON
                            set bedroom_lamp.power to DIMMED
                            set bedroom_heater.power to ON
                    }
                }""";

        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ProgramContext p = parser.program();
        Program testProgram = (Program) p.accept(visitor);
        Node testIfStmt = ((Action) testProgram.getStatements().get(5)).getStatements().getFirst();
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
        String legalInput = """
                type Switch {
                    enum state [ON, OFF]
                }
                type Light {
                    enum power [ON, OFF]
                }
                type DimmableLight inherits Light {
                    enum power [DIMMED]
                }
                type Heater {
                    enum power [ON, OFF]
                }
                room Bedroom {
                    bedroom_switch of Switch(OFF)
                    bedroom_light of Light(OFF)
                    bedroom_lamp of DimmableLight(OFF)
                    bedroom_heater of Heater(OFF)
                }
                action bedroom_all on bedroom_switch.state {
                    if bedroom_switch.state is ON {\n
                    }
                }
                """;
        AutomateSimParser parser = constructParser(legalInput);
        AutomateSimParser.ProgramContext p = parser.program();
        Program testProgram = (Program) p.accept(visitor);
        Node testIfStmt = ((Action) testProgram.getStatements().get(5)).getStatements().getFirst();
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

            try {
                AutomateSimParser.NumberContext n = parser.number();
                Node testNumberType = n.accept(visitor);
                fail("Expected exception");
            } catch (ParserException e) {
                //pass
            }
        }

    }

    @Test
    void stringTypeLegalTest() {
        String legalTestStrings = "string varname";

        AutomateSimParser parser = constructParser(legalTestStrings);

        AutomateSimParser.PropertyContext str = parser.property();
        Node value = str.accept(visitor);
        assertEquals("varname", ((StringType)value).getName().getText());
    }

    @Test
    void stringTypeErrorTest() {
        String[] testStrings = {"asdf", "", "string ", "string"};

        for (String s: testStrings) {
            AutomateSimParser parser = constructParser(s);

            try {
                AutomateSimParser.PropertyContext n = parser.property();
                Node value = n.accept(visitor);
                fail("Expected exception");
            } catch (ParserException e) {
                // pass
            }
        }
    }
    @Test
    void stringTypeErrorTest1() {
        String testString = "asdf";
        testStringTypeError(testString);
    }

    @Test
    void stringTypeErrorTest2() {
        String testString = "";
        testStringTypeError(testString);
    }

    @Test
    void stringTypeErrorTest3() {
        String testString = "string ";
        testStringTypeError(testString);
    }

    @Test
    void stringTypeErrorTest4() {
        String testString = "string";
        testStringTypeError(testString);
    }

    private void testStringTypeError(String testString) {
        AutomateSimParser parser = constructParser(testString);

        try {
            AutomateSimParser.PropertyContext n = parser.property();
            Node value = n.accept(visitor);
            fail("Expected exception");
        } catch (ParserException e) {
            // pass
        }
    }

    @Test
    void typeLegalTest() {
        String testString =
                """
                type Light {
                    enum PowerState [state0, state1]
                    string Location
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        AutomateSimParser.TypeContext t = parser.type();
        Type value = (Type) t.accept(visitor);
        assertEquals("Light", value.getName().getText());
        assertEquals(3, value.getProperties().size());
        assertEquals("Location", ((StringType) value.getProperties().get(1)).getName().getText());
        assertEquals("Brightness", ((NumberType) value.getProperties().get(2)).getName().getText());
        assertNull(value.getSupertype());
    }

    @Test
    void duplicateTypeErrorTest() {
        String testString =
                """
                type Light {
                    enum PowerState [state0, state1]
                    string Location
                    number Brightness [0, 10]
                }
                
                type Light {
                    enum PowerState [state0, state1]
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        AutomateSimParser.ProgramContext t = parser.program();
        try {
            Program value = (Program) t.accept(visitor);
            fail("Expected exception");
        } catch (ParserException ignored) {}
    }

    @Test
    void duplicateTypePropertyErrorTest() {
        String testString =
                """
                type Light {
                    enum PowerState [state0, state1]
                    string PowerState
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        AutomateSimParser.TypeContext t = parser.type();
        try {
            Type value = (Type) t.accept(visitor);
            fail("Expected exception");
        } catch (ParserException ignored) {}
    }

    @Test
    void typeInheritLegalTest() {
        String testString =
                """
                type Appliance {
                    enum PowerState [state0, state1]
                }
                
                type Light inherits Appliance {
                    string Location
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        AutomateSimParser.ProgramContext t = parser.program();
        Program value = (Program) t.accept(visitor);
        Type superType = (Type) value.getStatements().get(0);
        Type type = (Type) value.getStatements().get(1);

        assertEquals("Light", type.getName().getText());
        assertEquals(3, type.getProperties().size());
        assertEquals("Location", (type.getProperties().get(1)).getName().getText());
        assertEquals("Brightness", (type.getProperties().get(2)).getName().getText());
        assertEquals(superType, type.getSupertype());

    }

    @Test
    void typeInheritErrorTest() {
        String testString =
                """
                type Light inherits nothing {
                    enum PowerState [state0, state1]
                    string PowerState
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        try {
            AutomateSimParser.TypeContext t = parser.type();
            Type value = (Type) t.accept(visitor);
            fail("Expected exception");
        } catch (ParserException e) {
            // pass
        }
    }

    @Test
    void typeInheritErrorTest2() {
        String testString =
                """
                type Light inherits {
                    enum PowerState [state0, state1]
                    string PowerState
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        try {
            AutomateSimParser.TypeContext t = parser.type();
            Type value = (Type) t.accept(visitor);
            fail("Expected exception");
        } catch (ParserException e) {
            System.out.println(e);
            // pass
        }
    }

    @Test
    void typeInheritErrorTest3() {
        String testString =
                """
                type Light inher {
                    enum PowerState [state0, state1]
                    string Location
                    number Brightness [0, 10]
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        try {
            AutomateSimParser.TypeContext t = parser.type();
            Type value = (Type) t.accept(visitor);
            fail("Expected exception");
        } catch (ParserException e) {
            // pass
        }
    }

    @Test
    void setStatementLegalTest() {
        String testString =
                """
                type Switch {
                    enum state [ON, OFF]
                }
                type Light {
                    enum power [ON, OFF]
                }
                type DimmableLight inherits Light {
                    string name
                    number id [0, 100]
                    enum power [DIMMED]
                }
                type Heater {
                    enum power [ON, OFF]
                }
                room Bedroom {
                    bedroom_switch of Switch(OFF)
                    bedroom_light of Light(OFF)
                    bedroom_lamp of DimmableLight("help", 0, OFF)
                    bedroom_heater of Heater(OFF)
                }
                action bedroom_all on bedroom_switch.state {
                    if bedroom_switch.state is ON {
                        set bedroom_lamp.id to 1
                        set bedroom_lamp.power to DIMMED
                        set bedroom_lamp.name to "me"
                    }
                }
                """;

        AutomateSimParser parser = constructParser(testString);

        AutomateSimParser.ProgramContext t = parser.program();

        Program p = (Program) t.accept(visitor);
        SetStatement s0 = (SetStatement) ((IfStatement) ((Action)p.getStatements().getLast()).getStatements().getFirst()).getStatements().get(0);
        assertEquals(new DeviceProp(new Var("bedroom_lamp"), new Var("id")), s0.getDeviceProp());
        assertEquals("id" , s0.getStaticVal().getVarName());
        assertEquals("1" , s0.getStaticVal().getValue());
        SetStatement s1 = (SetStatement) ((IfStatement) ((Action)p.getStatements().getLast()).getStatements().getFirst()).getStatements().get(1);
        assertEquals(new DeviceProp(new Var("bedroom_lamp"), new Var("power")), s1.getDeviceProp());
        assertEquals("power" , s1.getStaticVal().getVarName());
        assertEquals("DIMMED" , s1.getStaticVal().getValue());
        SetStatement s2 = (SetStatement) ((IfStatement) ((Action)p.getStatements().getLast()).getStatements().getFirst()).getStatements().get(2);
        assertEquals(new DeviceProp(new Var("bedroom_lamp"), new Var("name")), s2.getDeviceProp());
        assertEquals("name" , s2.getStaticVal().getVarName());
        assertEquals("me" , s2.getStaticVal().getValue());
    }

    private static AutomateSimParser constructParser(String legalInput) {
        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AutomateSimParser automateSimParser = new AutomateSimParser(tokens);
        automateSimParser.setErrorHandler(new NoRecoverStrategy());
        return automateSimParser;
    }
}



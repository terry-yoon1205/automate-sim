package parser;

import ast.Action;
import ast.EnumType;
import ast.Node;
import ast.NumberType;
import ast.Var;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

    public class ParserTest {
        static ParseTreeToAST visitor;

        public ParserTest() {
        }

        @BeforeAll
        public static void initialization() {
            visitor = new ParseTreeToAST();
        }

        @Test
        void ActionTest() {
            String legalInput = "action sync_heaters on [main_heater.level, bedroom_heater.level] {\n    set bedroom_heater.level to main_heater.level\n}";
            AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
            lexer.reset();
            TokenStream tokens = new CommonTokenStream(lexer);
            AutomateSimParser parser = new AutomateSimParser(tokens);
            AutomateSimParser.ActionContext a = parser.action();
            Node testAction = (Node)a.accept(visitor);
            Assertions.assertEquals(Action.class, testAction.getClass());
            Assertions.assertEquals("sync_heaters", testAction.getClass().getName());
        }

        @Test
        void DeviceTest() {
            String legalInput = "bedroom_switch of Switch(OFF)";
            AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
            lexer.reset();
            TokenStream tokens = new CommonTokenStream(lexer);
            AutomateSimParser parser = new AutomateSimParser(tokens);
            AutomateSimParser.DeviceContext d = parser.device();
            Node testDevice = (Node)d.accept(visitor);
        }

        @Test
        void EnumTypeTest() {
            String legalInput = "enum power [ON, OFF]";
            AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
            lexer.reset();
            TokenStream tokens = new CommonTokenStream(lexer);
            AutomateSimParser parser = new AutomateSimParser(tokens);
            AutomateSimParser.EnumContext e = parser.enum_();
            Node testEnumType = (Node)e.accept(visitor);
            Assertions.assertEquals(((EnumType)testEnumType).getName(), new Var("power"));
            Assertions.assertEquals(((EnumType)testEnumType).getStates(), List.of(new Var("ON"), new Var("OFF")));
        }

        @Test
        void numberTypeTest() {
            String legalInput = "number level [0, 10]";
            AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(legalInput));
            lexer.reset();
            TokenStream tokens = new CommonTokenStream(lexer);
            AutomateSimParser parser = new AutomateSimParser(tokens);
            String legal = "number level [0, 10]";
            String noMax = "number level [0, ]";
            String noMin = "number level [, 10]";
            AutomateSimParser.NumberContext n = parser.number();
            Node testNumberType = (Node)n.accept(visitor);
            Assertions.assertEquals(new Var("level"), ((NumberType)testNumberType).getName());
            Assertions.assertEquals(0, ((NumberType)testNumberType).getMin());
            Assertions.assertEquals(10, ((NumberType)testNumberType).getMax());
        }
    }



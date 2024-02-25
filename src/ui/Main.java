package ui;

import ast.*;
import evaluator.AutomateSimEvaluator;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.AutomateSimLexer;
import parser.AutomateSimParser;
import parser.NoRecoverStrategy;
import parser.ParseTreeToAST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "code.txt";

    public static void main(String[] args) {
        readFile();

        AutomateSim sim = new AutomateSim();
        sim.run();
    }

    private static void readFile(){
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        String code = content.toString();

        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString(code));
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AutomateSimParser parser = new AutomateSimParser(tokens);
        parser.setErrorHandler(new NoRecoverStrategy());

        ParseTreeToAST visitor = new ParseTreeToAST();

        AutomateSimParser.ProgramContext p = parser.program();
        Program program = (Program) p.accept(visitor);

        AutomateSimEvaluator eval = new AutomateSimEvaluator();
        eval.visit(null, program);
    }
}

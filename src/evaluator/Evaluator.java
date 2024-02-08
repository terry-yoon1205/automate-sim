package evaluator;

import ast.*;

import java.util.HashMap;
import java.util.Map;

public class Evaluator implements Visitor {
    // Map to store our current variable assignments
    private final Map<Var, Integer> environment = new HashMap<>();

    @Override
    public Object visit(Object context, Program p) {
        return null;
    }

    @Override
    public Object visit(Object context, Action p) {
        return null;
    }

    @Override
    public Object visit(Object context, Decl p) {
        return null;
    }

    @Override
    public Object visit(Object context, Var p) {
        return null;
    }

    @Override
    public Object visit(Object context, Room p) {
        return null;
    }

    @Override
    public Object visit(Object context, Type p) {
        return null;
    }

    @Override
    public Object visit(Object context, NumberVal p) {
        return null;
    }

    @Override
    public Object visit(Object context, StringVal p) {
        return null;
    }

    @Override
    public Object visit(Object context, EnumVal p) {
        return null;
    }
}

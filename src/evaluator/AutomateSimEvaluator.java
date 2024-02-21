package evaluator;

import ast.*;
import ast.Action;
import ast.Device;
import model.*;

import java.util.*;

public class AutomateSimEvaluator implements Visitor<Void, Void> {
    @Override
    public Void visit(Void context, Program p) {
        for (Decl d : p.getStatements()) {
            d.accept(context, this);
        }

        return null;
    }

    @Override
    public Void visit(Void context, Action p) {
        return null;
    }

    @Override
    public Void visit(Void context, Decl p) {
        return null;
    }

    @Override
    public Void visit(Void context, Var p) {
        return null;
    }

    @Override
    public Void visit(Void context, Room p) {
        return null;
    }

    @Override
    public Void visit(Void context, Type p) {
        return null;
    }

    @Override
    public Void visit(Void context, NumberVal p) {
        return null;
    }

    @Override
    public Void visit(Void context, StringVal p) {
        return null;
    }

    @Override
    public Void visit(Void context, EnumVal p) {
        return null;
    }

    @Override
    public Void visit(Void context, NumberType p) {
        return null;
    }

    @Override
    public Void visit(Void context, StringType p) {
        return null;
    }

    @Override
    public Void visit(Void context, EnumType p) {
        return null;
    }

    @Override
    public Void visit(Void context, DeviceProp p) {
        return null;
    }

    @Override
    public Void visit(Void context, Device p) {
        return null;
    }

    @Override
    public Void visit(Void context, SetStatement p) {
        return null;
    }

    @Override
    public Void visit(Void context, IfStatement p) {
        return null;
    }

    @Override
    public Void visit(Void context, ForStatement p) {
        return null;
    }
}

package evaluator;

import ast.*;
import model.Property;

public abstract class AutomateSimBaseVisitor<C, T> implements Visitor<C, T> {
    public T visit(C context, Program p) {
        for (Decl d : p.getStatements()) {
            d.accept(context, this);
        }

        return null;
    }

    public T visit(C context, Action p) {
        for (DeviceProp d : p.getTriggers()) {
            d.accept(context, this);
        }
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        return null;
    }

    public T visit(C context, Var p) {
        return null;
    }

    public T visit(C context, Room p) {
        for (Device d : p.getDevices()) {
            d.accept(context, this);
        }

        return null;
    }

    public T visit(C context, Type p) {
        return null;
    }

    public T visit(C context, NumberVal p) {
        return null;
    }

    public T visit(C context, StringVal p) {
        return null;
    }

    public T visit(C context, EnumVal p) {
        return null;
    }

    public T visit(C context, NumberType p) {
        return null;
    }

    public T visit(C context, StringType p) {
        return null;
    }

    public T visit(C context, EnumType p) {
        return null;
    }

    public T visit(C context, DeviceProp p) {
        return null;
    }

    public T visit(C context, Device p) {
        for (PropVal pv : p.getValues()) {
            pv.accept(context, this);
        }

        return null;
    }

    public T visit(C context, SetStatement p) {
        return null;
    }

    public T visit(C context, IfStatement p) {
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        return null;
    }

    public T visit(C context, ForStatement p) {
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        return null;
    }
}

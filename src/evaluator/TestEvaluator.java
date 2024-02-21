package evaluator;

import ast.*;
import ast.Action;
import ast.Device;
import model.*;

import java.util.*;

public class TestEvaluator extends AutomateSimBaseVisitor<StringBuilder, Object> {
    @Override
    public Object visit(StringBuilder context, Action p) {
        context.append("\nAction: ");
        context.append(p.getName());

        for (DeviceProp d : p.getTriggers()) {
            d.accept(context, this);
        }
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        return null;
    }

    @Override
    public Object visit(StringBuilder context, Var p) {
        context.append(" ");
        context.append(p.getText());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Room r) {
        System.out.println("ROOM");

        context.append("\nRoom: ");
        context.append(r.getName());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Type p) {
        context.append("\nType: ");
        context.append(p.getName());

        List<PropType> allProps = new ArrayList<>(p.getProperties());

        if (p.getSupertype() != null) {
            allProps.addAll(p.getSupertype().getProperties());
        }

        for (PropType prop : allProps) {
            prop.accept(context, this);
        }

        return null;
    }

    @Override
    public Object visit(StringBuilder context, NumberVal p) {
        context.append("\nNum: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, StringVal p) {
        context.append("\nString: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, EnumVal p) {
        context.append("\nEnum: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, NumberType p) {
        context.append("\nNum: ");
        context.append("[")
                .append(p.getMin())
                .append(",")
                .append(p.getMax())
                .append("]\n");

        return null;
    }

    @Override
    public Object visit(StringBuilder context, StringType p) {
        return null;
    }

    @Override
    public Object visit(StringBuilder context, EnumType p) {
        context.append("\nEnum: ");
        context.append("{");

        for (Var v : p.getStates()) {
            context.append(v.getText()).append(",");
        }

        context.append("}\n");
        return null;
    }

    @Override
    public Object visit(StringBuilder context, DeviceProp p) {
        System.out.println("DEVICEPROP");
        context.append(p.getDevice()).append(".").append(p.getProp());

        return null;
    }

    @Override
    public Object visit(StringBuilder context, Device p) {
        model.Device d = new model.Device(p.getName().getText());

        for (PropVal pv  : p.getValues()) {
            PropType type = pv.getType();
            Property prop;
            switch (type) {
                case EnumType enumType -> {
                    List<Var> vars = ((EnumVal) pv).getType().getStates();
                    Set<String> states = new HashSet<>();
                    for (Var v : vars) {
                        states.add(v.getText());
                    }
                    prop = new EnumProp(
                            pv.getVarName(),
                            pv.getValue(),
                            states);
                }
                case NumberType numberType -> prop = new NumberProp(
                        pv.getVarName(),
                        pv.getValue(),
                        ((NumberVal) pv).getType().getMin(),
                        ((NumberVal) pv).getType().getMax());
                case StringType stringType -> prop = new StringProp(
                        pv.getVarName(),
                        pv.getValue());
                case null, default -> {
                    // throw error?
                    return null;
                }
            }
            d.addProp(prop);
        }

        context.append("\n");
        context.append(p.getName().getText());

        return null;
    }

    @Override
    public Object visit(StringBuilder context, SetStatement p) {
        context.append("\nSET: ");
        context.append(p.getDeviceProp().getProp().getText())
                .append(" = ")
                .append(p.getStaticVal());

        return null;
    }

    @Override
    public Object visit(StringBuilder context, IfStatement p) {
        context.append("\nIF: ");
        context.append(p.getValue());

        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        return null;
    }

    @Override
    public Object visit(StringBuilder context, ForStatement p) {
        context.append("\nFOR: ");
        context.append("{");

        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        context.append("}");
        return null;
    }

}

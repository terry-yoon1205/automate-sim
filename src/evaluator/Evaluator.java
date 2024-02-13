package evaluator;

import ast.*;
import ast.Action;
import ast.Device;
import model.*;

import java.util.*;

public class Evaluator implements Visitor<StringBuilder, Object> {
    List<Stmt> tempStmt = new ArrayList<>();

    @Override
    public Object visit(StringBuilder context, Program p) {
        for (Decl d : p.getStatements()) {
            d.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Action p) {
        model.Action action = new model.Action();
        tempStmt.clear();
        context.append("\nAction: ");
        context.append(p.getName());

        for (DeviceProp d : p.getTriggers()) {
            d.accept(context, this);
        }
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        for(Stmt st : tempStmt){
            action.add(st);
        }

        // TODO
        Context.addAction(p.getName().getText(), action);


        return null;
    }

    @Override
    public Object visit(StringBuilder context, Decl p) {
        // this is not a real class
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
        Set<String> deviceNames = new HashSet<>();

        for (Device d : r.getDevices()) {
            deviceNames.add(d.getName().toString());
            d.accept(context, this);
        }

        Context.addRoom(r.getName().toString(), deviceNames);

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

        NumberProp numberProp = new NumberProp(
                p.getVarName(),
                String.valueOf(p.getValue()),
                p.getType().getMin(),
                p.getType().getMax());

        context.append("\nNum: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, StringVal p) {
        StringProp stringProp = new StringProp(
                p.getVarName(),
                p.getValue());

        context.append("\nString: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, EnumVal p) {

        Set<String> enumStates = new HashSet<>();
        for (Var v : p.getType().getStates()) {
            enumStates.add(v.getText());
        }
        EnumProp enumProp = new EnumProp(
                p.getVarName(),
                p.getValue(),
                enumStates);

        context.append("\nEnum: ");
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, NumberType p) {

        context.append("\nNum: ");
        context.append("[" + p.getMin() + "," + p.getMax() + "]\n");
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
            context.append(v.getText() + ",");
        }
        context.append("}\n");
        return null;
    }

    @Override
    public Object visit(StringBuilder context, DeviceProp p) {
        System.out.println("DEVICEPROP");
//        model.Device d = new model.Device(p.getDevice().toString());

        context.append(p.getDevice() + "." + p.getProp());
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


        Context.addDevice(d);

        context.append("\n");
        context.append(p.getName().getText());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, SetStatement p) {
        SetStmt setStmt;
        if(p.getStaticVal() != null){
            setStmt = new SetStmt(
                    p.getDeviceProp().getDevice().getText(),
                    p.getDeviceProp().getProp().getText(),
                    p.getStaticVal().getVarName());
        }else{
            setStmt = new SetStmt(
                    p.getDeviceProp().getDevice().getText(),
                    p.getDeviceProp().getProp().getText(),
                    p.getDynamicVal().getDevice().getText(),
                    p.getDynamicVal().getProp().getText());
        }

        tempStmt.add(setStmt);

        context.append("\nSET: ");
        context.append(p.getDeviceProp().getProp().getText() + " = " + p.getStaticVal());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, IfStatement p) {
        IfStmt ifStmt = new IfStmt(
                p.getDeviceProp().getDevice().getText(),
                p.getDeviceProp().getProp().getText(),
                p.getValue().getValue()
        );

        context.append("\nIF: ");
        context.append(p.getValue());
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        tempStmt.add(ifStmt);

        return null;
    }

    @Override
    public Object visit(StringBuilder context, ForStatement p) {
        ForStmt forStmt = new ForStmt(
                p.getType().getName().getText(),
                p.getRoom().getName().getText());

        context.append("\nFOR: ");
        context.append("{");
        for (Statement s : p.getStatements()) {
            s.accept(context, this);
        }

        tempStmt.add(forStmt);

        context.append("}");
        return null;
    }

}

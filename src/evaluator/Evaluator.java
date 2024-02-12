package evaluator;

import ast.*;
import model.Context;
import model.EnumProp;
import model.NumberProp;
import model.StringProp;

import java.util.*;

public class Evaluator implements Visitor<StringBuilder, Object> {
    // Map to store our current variable assignments
    private final Map<Var, Integer> environment = new HashMap<>();
    private final Map<Integer, Integer> memory = new HashMap<>();

//    private final Map<String, Prop> propTypes = new HashMap<>();

    private int memptr = 0;

    private Integer getFreshLocation() {
        Integer loc = memptr;
        memptr = memptr + 1;
        return loc;
    }

    @Override
    public Object visit(StringBuilder context, Program p) {
        for (Decl d : p.getStatements()) {
            d.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Action p) {

        Integer location = getFreshLocation();
        environment.put(p.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

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

        Integer location = getFreshLocation();
        environment.put(r.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

        Set<Device> devices = new HashSet<>(r.getDevices()); // move to global in eval?

        Context.addRoom(r.getName().toString(), devices);

        context.append("\nRoom: ");
        context.append(r.getName());
        for (Device d : r.getDevices()) {
            d.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Type p) {

        Integer location = getFreshLocation();
        environment.put(p.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

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
        context.append(p.getState());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, NumberType p) {

//        NumberProp numberProp = new NumberProp(p.getName(), p.get);
        context.append("\nNum: ");
        context.append("[" + p.getMin() + "," + p.getMax() + "]");
        return null;
    }

    @Override
    public Object visit(StringBuilder context, StringType p) {

//    StringProp stringProp = new StringProp(p.getName().toString(), );

        return null;
    }

    @Override
    public Object visit(StringBuilder context, EnumType p) {
        context.append("\nEnum: ");

        Set<String> enumStates = new HashSet<>();
        for (Var v : p.getStates()) {
            enumStates.add(v.getText());
        }

        EnumProp enumProp = new EnumProp(p.getName().toString(), "???", enumStates);

        context.append("{");
        for (Var v : p.getStates()) {
            context.append(v.getText() + ",");
        }
        context.append("}");
        return null;
    }

    @Override
    public Object visit(StringBuilder context, DeviceProp p) {
        model.Device d = new model.Device(p.getDevice().toString());
    /*    p.getProp();
        d.addProp();*/
        Context.addDevice(d);

        context.append("\nTrigger on: ");
        context.append(p.getDevice() + "." + p.getProp());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, SetStatement p) {
        context.append("\nSET: ");
        context.append(p.getDeviceProp().getProp().getText() + " = " + p.getStaticVal());
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

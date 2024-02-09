package evaluator;

import ast.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Evaluator implements Visitor<StringBuilder,Object> {
    // Map to store our current variable assignments
    private final Map<Var, Integer> environment = new HashMap<>();
    private final Map<Integer, Integer> memory = new HashMap<>();
    private int memptr = 0;

    private Integer getFreshLocation() {
        Integer loc = memptr;
        memptr = memptr + 1;
        return loc;
    }
    @Override
    public Object visit(StringBuilder context, Program p) {
        for (Decl d: p.getStatements()){
            d.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Action p) {

        Integer location = getFreshLocation();
        environment.put(p.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

        context.append(p.getName());
        for (DeviceProp d: p.getTriggers()){
            d.accept(context, this);
        }
        for (Statement s: p.getStatements()){
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
        context.append(p.getText());
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Room p) {

        Integer location = getFreshLocation();
        environment.put(p.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

        context.append(p.getName());
        for(Device d: p.getDevices()){
            d.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, Type p) {

        Integer location = getFreshLocation();
        environment.put(p.getName(), location); // declare variable (no initialisation yet; you might also prefer a default initialisation)
        memory.put(location, null);

        context.append(p.getName());
        for(PropType prop: p.getProperties()){
            prop.accept(context, this);
        }
        return null;
    }

    @Override
    public Object visit(StringBuilder context, NumberVal p) {
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, StringVal p) {
        context.append(p.getValue());

        System.out.println(context); // print StringBuilder

        return null;
    }

    @Override
    public Object visit(StringBuilder context, EnumVal p) {
        context.append(p.getState());

        System.out.println(context); // print StringBuilder

        return null;
    }
}

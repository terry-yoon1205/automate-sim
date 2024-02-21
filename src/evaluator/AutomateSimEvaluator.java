package evaluator;

import ast.*;
import ast.Action;
import ast.Device;
import model.*;
import model.interfaces.Parent;

import java.util.HashMap;

public class AutomateSimEvaluator implements Visitor<Parent, String> {
    private final HashMap<String, model.Action> actions = new HashMap<>();

    @Override
    public String visit(Parent context, Program p) {
        for (Decl d : p.getStatements()) {
            d.accept(context, this);
        }

        return null;
    }

    @Override
    public String visit(Parent context, Action p) {
        model.Action action = new model.Action();

        String actionName = p.getName().getText();
        actions.put(actionName, action);

        for (DeviceProp d : p.getTriggers()) {
            String deviceName = d.getDevice().getText();
            String propName = d.getProp().getText();

            // this will never be null, the device and property should've already been added to `Context`
            Property prop = Context.getDevice(deviceName).getProp(propName);
            prop.addObserver(action);
        }

        for (Statement s : p.getStatements()) {
            s.accept(action, this);
        }

        return null;
    }

    @Override
    public String visit(Parent context, Var p) {
        return null;
    }

    @Override
    public String visit(Parent context, Room p) {
        return null;
    }

    @Override
    public String visit(Parent context, Type p) {
        return null;
    }

    @Override
    public String visit(Parent context, NumberVal p) {
        return null;
    }

    @Override
    public String visit(Parent context, StringVal p) {
        return null;
    }

    @Override
    public String visit(Parent context, EnumVal p) {
        return null;
    }

    @Override
    public String visit(Parent context, NumberType p) {
        return null;
    }

    @Override
    public String visit(Parent context, StringType p) {
        return null;
    }

    @Override
    public String visit(Parent context, EnumType p) {
        return null;
    }

    @Override
    public String visit(Parent context, Device p) {
        return null;
    }

    @Override
    public String visit(Parent context, SetStatement p) {
        SetStmt stmt;

        String deviceName = p.getDeviceProp().getDevice().getText();
        String propName = p.getDeviceProp().getProp().getText();
        if (p.getStaticVal() != null) {
            stmt = new SetStmt(deviceName, propName, p.getStaticVal().getValue());
        } else {
            stmt = new SetStmt(deviceName, propName,
                    p.getDynamicVal().getDevice().getText(),
                    p.getDynamicVal().getProp().getText());
        }

        context.add(stmt);
        return null;
    }

    @Override
    public String visit(Parent context, IfStatement p) {
        String deviceName = p.getDeviceProp().getDevice().getText();
        String propName = p.getDeviceProp().getProp().getText();
        String ifVal = p.getValue().getValue();

        IfStmt stmt = new IfStmt(deviceName, propName, ifVal);
        context.add(stmt);

        // add child statements
        for (Statement s : p.getStatements()) {
            s.accept(stmt, this);
        }

        return null;
    }

    @Override
    public String visit(Parent context, ForStatement p) {
        ForStmt stmt = new ForStmt();
        context.add(stmt);

        // add devices of the specified type in that are in the specified room
        for (Device d : p.getRoom().getDevices()) {
            Type deviceType = d.getType();
            boolean typeMatch = false;
            while ((deviceType = deviceType.getSupertype()) != null) {
                if (deviceType == p.getType()) {
                    typeMatch = true;
                    break;
                }
            }

            if (typeMatch) {
                stmt.addDevice(d.getName().getText());
            }
        }

        // add child statements
        for (Statement s : p.getStatements()) {
            s.accept(stmt, this);
        }

        return null;
    }

    @Override
    public String visit(Parent context, DeviceProp p) {
        // not needed
        return null;
    }

}

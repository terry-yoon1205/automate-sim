package evaluator;

import ast.*;
import ast.Action;
import ast.Device;
import model.*;
import model.context.Context;
import model.context.TestContext;
import model.interfaces.Parent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomateSimEvaluator extends AutomateSimBaseVisitor<Parent, Property> {
    @Override
    public Property visit(Parent parent, Action p) {
        model.Action action = new model.Action();

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

        /* TESTING */
        TestContext.addAction(p.getName().getText(), action);

        return null;
    }

    @Override
    public Property visit(Parent parent, Type p) {
        // type information is not retained in the program
        // only used during checks and evaluation
        return null;
    }

    @Override
    public Property visit(Parent parent, Room p) {
        String roomName = p.getName().getText();
        Set<String> deviceNames = new HashSet<>();

        for (Device d : p.getDevices()) {
            deviceNames.add(d.getName().getText());
            d.accept(parent, this);
        }

        /* TESTING */
        TestContext.addRoom(roomName, deviceNames);

        return null;
    }

    @Override
    public Property visit(Parent parent, Device p) {
        String name = p.getName().getText();
        model.Device device = new model.Device(name);

        for (PropVal pv : p.getValues()) {
            Property prop = pv.accept(parent, this);
            device.addProp(prop);
        }

        Context.addDevice(device);
        return null;
    }

    @Override
    public Property visit(Parent parent, NumberVal p) {
        String name = p.getVarName();
        String value = p.getValue();
        int min = p.getType().getMin();
        int max = p.getType().getMax();

        return new NumberProp(name, value, min, max);
    }

    @Override
    public Property visit(Parent parent, StringVal p) {
        return new StringProp(p.getVarName(), p.getValue());
    }

    @Override
    public Property visit(Parent parent, EnumVal p) {
        List<Var> astStates = p.getType().getStates();
        Set<String> modelStates = new HashSet<>();

        for (Var v : astStates) {
            modelStates.add(v.getText());
        }

        return new EnumProp(p.getVarName(), p.getValue(), modelStates);
    }

    @Override
    public Property visit(Parent parent, SetStatement p) {
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

        parent.add(stmt);
        return null;
    }

    @Override
    public Property visit(Parent parent, IfStatement p) {
        String deviceName = p.getDeviceProp().getDevice().getText();
        String propName = p.getDeviceProp().getProp().getText();
        String ifVal = p.getValue().getValue();

        IfStmt stmt = new IfStmt(deviceName, propName, ifVal);
        parent.add(stmt);

        // add child statements
        for (Statement s : p.getStatements()) {
            s.accept(stmt, this);
        }

        return null;
    }

    @Override
    public Property visit(Parent parent, ForStatement p) {
        ForStmt stmt = new ForStmt();
        parent.add(stmt);

        // add devices of the specified type in that are in the specified room
        for (Device d : p.getRoom().getDevices()) {
            Type deviceType = d.getType();

            if (p.getType() == deviceType || p.getType() == deviceType.getSupertype()) {
                stmt.addDevice(d.getName().getText());
            }
        }

        // add child statements
        for (Statement s : p.getStatements()) {
            s.accept(stmt, this);
        }

        return null;
    }
}

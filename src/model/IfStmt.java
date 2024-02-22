package model;

import model.context.Context;
import model.interfaces.Parent;

public class IfStmt extends Stmt implements Parent { // composite of the composite pattern
    private final String val;

    public IfStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public void execute() {
        Property p = Context.getDevice(device).getProp(prop);
        if (!p.getValue().equals(val))
            return;

        for (Stmt s : children) {
            s.execute();
        }
    }
}

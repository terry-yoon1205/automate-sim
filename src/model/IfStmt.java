package model;

import model.context.Context;
import model.interfaces.Parent;

import java.util.ArrayList;

public class IfStmt extends Stmt { // composite of the composite pattern
    private final String val;

    public IfStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
        this.children = new ArrayList<>();
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

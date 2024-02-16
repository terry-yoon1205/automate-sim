package model;

import java.util.ArrayList;
import java.util.List;

public class IfStmt extends Stmt { // composite of the composite pattern
    private final String val;
    private final List<Stmt> children;

    public IfStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
        this.children = new ArrayList<>();
    }

    public String getVal() {
        return val;
    }

    public void addStmt(Stmt stmt) {
        children.add(stmt);
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

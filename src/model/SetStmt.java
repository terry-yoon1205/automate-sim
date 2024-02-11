package model;

public class SetStmt extends Stmt { // leaf of the composite pattern
    private final String val;
    private final Property src;

    public SetStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
        this.src = null;
    }

    public SetStmt(String device, String prop, Property src) {
        super(device, prop);
        this.val = null;
        this.src = src;
    }

    @Override
    public void execute() {
        // TODO
    }
}

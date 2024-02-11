package model;

public class IfStmt extends Stmt { // composite of the composite pattern
    private final String val;

    public IfStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
    }

    @Override
    public void execute() {
        // TODO
    }
}

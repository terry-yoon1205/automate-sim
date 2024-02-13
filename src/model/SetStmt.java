package model;

public class SetStmt extends Stmt { // leaf of the composite pattern
    private final String val;
    private final String srcName;
    private final String srcVal;

    public SetStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
        this.srcName = null;
        this.srcVal = null;
    }

    public SetStmt(String device, String prop, String srcName, String srcVal) {
        super(device, prop);
        this.val = null;
        this.srcName = srcName;
        this.srcVal = srcVal;
    }

    public String getVal() {
        return val;
    }

    public String getSrcName() {
        return srcName;
    }

    public String getSrcVal() {
        return srcVal;
    }

    @Override
    public void execute() {
        // TODO
    }
}

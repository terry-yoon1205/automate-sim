package model;

import model.context.Context;
import model.context.TestContext;

public class SetStmt extends Stmt { // leaf of the composite pattern
    private final String val;
    private final String srcDevice;
    private final String srcProp;

    public SetStmt(String device, String prop, String val) {
        super(device, prop);
        this.val = val;
        this.srcDevice = null;
        this.srcProp = null;
        this.children = null;   // set statement doesn't have children
    }

    public SetStmt(String device, String prop, String srcDevice, String srcProp) {
        super(device, prop);
        this.val = null;
        this.srcDevice = srcDevice;
        this.srcProp = srcProp;
        this.children = null;
    }

    public String getVal() {
        return val;
    }

    public String getSrcDevice() {
        return srcDevice;
    }

    public String getSrcProp() {
        return srcProp;
    }

    @Override
    public void execute() {
        Property p = Context.getDevice(device).getProp(prop);
        String result;

        if (val != null) {
            p.mutate(val);  // should never return false
            result = val;
        } else {
            Property other = Context.getDevice(srcDevice).getProp(srcProp);
            p.mutate(other.getValue());     // also should never return false
            result = other.getValue();
        }

        String out = String.format("%s of %s has been changed to %s.", prop, device, result);
        TestContext.print(out);
    }
}

package ast;

// reference to value of a specific prop in a device
public class DeviceProp extends Node {
    private final Var device;
    private final Var prop;

    public DeviceProp(Var device, Var prop) {
        this.device = device;
        this.prop = prop;
    }

    public Var getDevice() {
        return device;
    }

    public Var getProp() {
        return prop;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

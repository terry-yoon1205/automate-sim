package ast;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceProp that)) return false;
        return Objects.equals(device, that.device) && Objects.equals(prop, that.prop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, prop);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

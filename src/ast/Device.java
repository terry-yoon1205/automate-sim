package ast;

import java.util.List;
import java.util.Objects;

public class Device extends Node {
    private final Var name;
    private final Type type;
    private final List<PropVal> values;

    public Device(Var name, Type type, List<PropVal> values) {
        this.name = name;
        this.type = type;
        this.values = values;
    }

    public Var getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public List<PropVal> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device device)) return false;
        return Objects.equals(name, device.name) && Objects.equals(type, device.type) && Objects.equals(values, device.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, values);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

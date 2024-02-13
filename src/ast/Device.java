package ast;

import java.util.List;

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
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

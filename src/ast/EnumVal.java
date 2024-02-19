package ast;

import java.util.Objects;

public class EnumVal extends PropVal {
    private final Var state;

    // name should always be the property name!
    public EnumVal(String name, Var state, PropType type) {
        this.name = name;
        this.state = state;
        this.type = type;
    }

    public String getValue() {
        return state.getText();
    }

    @Override
    public String toString() {
        return state.getText();
    }

    @Override
    public EnumType getType() {
        return (EnumType) type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnumVal enumVal)) return false;
        return Objects.equals(state, enumVal.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

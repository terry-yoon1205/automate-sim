package ast;

import java.util.Objects;

public class NumberVal extends PropVal {
    private final String value;

    public NumberVal(String name, String value, PropType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public NumberType getType() {
        return (NumberType) type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberVal numberVal)) return false;
        return Objects.equals(value, numberVal.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}
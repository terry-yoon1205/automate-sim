package ast;

import java.util.Objects;

public class StringVal extends PropVal {
    private final String value;

    public StringVal(String name, String value, PropType type) {
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
    public StringType getType() {
        return (StringType) type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringVal stringVal)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(value, stringVal.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

package ast;

import java.util.Objects;

public class NumberType extends PropType {
    private final Var name;
    private final int min;
    private final int max;

    public NumberType(Var name, int min, int max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public Var getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberType that)) return false;
        return min == that.min && max == that.max && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, min, max);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

package ast;

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
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

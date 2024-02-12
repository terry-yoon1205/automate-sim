package ast;

public class NumberVal extends PropVal {
    private final int value;

    public NumberVal(int value, PropType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}
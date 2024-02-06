package ast;

public class PropNumber extends Property {
    private final int value;

    public PropNumber(int value) {
        this.value = value;
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
package ast;

public class PropEnum extends Property{
    private final Enum value;

    public PropEnum(Enum value) {
        this.value = value;
    }

    public Enum getValue() {
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

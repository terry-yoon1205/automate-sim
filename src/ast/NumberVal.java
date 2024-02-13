package ast;

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
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}
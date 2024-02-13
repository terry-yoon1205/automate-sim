package ast;

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
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

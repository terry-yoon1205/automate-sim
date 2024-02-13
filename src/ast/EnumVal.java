package ast;

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
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

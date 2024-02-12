package ast;

public class EnumVal extends PropVal {
    private final Var state;

    public EnumVal(Var state, PropType type) {
        this.state = state;
        this.type = type;
    }

    public Var getState() {
        return state;
    }

    @Override
    public String toString() {
        return state.getText();
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

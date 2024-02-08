package ast;

import java.util.List;

public class EnumVal extends PropVal {
    private final Var state;

    public EnumVal(Var state) {
        this.state = state;
    }

    public Var getState() {
        return state;
    }

    @Override
    public String toString() {
        return state.getText();
    }

    @Override
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}

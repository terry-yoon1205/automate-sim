package ast;

import java.util.List;

public class EnumType extends PropType {
    private final List<Var> states;

    public EnumType(Var name, List<Var> states) {
        this.name = name;
        this.states = states;
    }

    public List<Var> getStates() {
        return states;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

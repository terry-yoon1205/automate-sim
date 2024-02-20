package ast;

public class StringType extends PropType {

    public StringType(Var name) {
        this.name = name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

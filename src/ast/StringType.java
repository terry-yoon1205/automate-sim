package ast;

public class StringType extends PropType {

    public StringType(Var name) {
        this.name = name;
    }

    @Override
    public String getTypeName() {
        return "string";
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

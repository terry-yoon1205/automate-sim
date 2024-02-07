package ast;

public class StringType extends PropType {
    private final Var name;

    public StringType(Var name) {
        this.name = name;
    }

    public Var getName() {
        return name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

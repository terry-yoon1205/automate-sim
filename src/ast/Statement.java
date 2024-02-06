package ast;

public class Statement extends Node {
    private final Name name;

    public Statement(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

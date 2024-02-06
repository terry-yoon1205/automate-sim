package ast;

public class Device extends Node{
    private final Name name;

    public Device(Name name) {
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

package ast;

// subclasses of PropType represents the property declarations in a type declaration
public abstract class PropType extends Node {
    protected Var name;

    public Var getName() {
        return name;
    }

    public abstract String getTypeName();
}

package ast;

import java.util.List;

public class Type extends Decl {
    private final Var name;
    private final Type supertype; // supertype that this type inherits properties from, null if DNE
    private final List<PropType> properties; // names of the properties this type has

    public Type(Var var, Type supertype, List<PropType> properties) {
        this.properties = properties;
        this.supertype = supertype;
        this.name = var;
    }

    public List<PropType> getProperties() {
        return properties;
    }

    public Var getName() {
        return name;
    }

    public Type getSupertype() {
        return supertype;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

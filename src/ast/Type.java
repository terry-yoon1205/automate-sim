package ast;

import java.util.List;

public class Type extends Decl {
    private final Name name;
    private final List<Property> properties;
    public Type(Name name, List<Property> properties) {
        this.properties = properties;
        this.name = name;
    }

    public List<Property> getProperties() {
        return properties;
    }
    public Name getName() {
        return name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

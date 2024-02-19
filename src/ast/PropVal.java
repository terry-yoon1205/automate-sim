package ast;

import java.util.Objects;

// subclasses of PropVal represents the value of the property that a device holds
public abstract class PropVal extends Node {
    protected String name; // variable name
    protected PropType type;

    public String getVarName(){
        return name;
    }
    public abstract PropType getType();

    public abstract String getValue();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropVal propVal)) return false;
        return Objects.equals(name, propVal.name) && Objects.equals(type, propVal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}

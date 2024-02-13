package ast;

// subclasses of PropVal represents the value of the property that a device holds
public abstract class PropVal extends Node {
    protected String name; // variable name
    protected PropType type;

    public String getVarName(){
        return name;
    }
    public abstract PropType getType();

    public abstract String getValue();
}

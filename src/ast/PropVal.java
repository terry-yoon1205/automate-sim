package ast;

// subclasses of PropVal represents the value of the property that a device holds
public abstract class PropVal extends Node {
    protected PropType type;
    public PropType getType(){
        return type;
    }
}

package model;

import model.interfaces.Parent;

public abstract class Stmt extends Parent { // component of the composite pattern
    protected String device;
    protected String prop;

    public Stmt(String device, String prop) {
        this.device = device;
        this.prop = prop;
    }


    public void setDevice(String device) {
        this.device = device;
    }

    public abstract void execute();
}

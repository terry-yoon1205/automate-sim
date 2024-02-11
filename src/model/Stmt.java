package model;

public abstract class Stmt { // component of the composite pattern
    private String device;
    private String prop;

    public Stmt(String device, String prop) {
        this.device = device;
        this.prop = prop;
    }

    public abstract void execute();
}

package ast;

import java.util.List;

public class Room extends Decl {
    private final Var name;
    private final List<Device> devices;

    public Room(Var var, List<Device> devices) {
        this.devices = devices;
        this.name = var;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Var getName() {
        return name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

package ast;

import java.util.List;

public class Room extends Decl {
    private final Var var;
    private final List<Device> devices;

    public Room(Var var, List<Device> devices) {
        this.devices = devices;
        this.var = var;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Var getName() {
        return var;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

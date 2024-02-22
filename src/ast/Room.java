package ast;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(name, room.name) && Objects.equals(devices, room.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, devices);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

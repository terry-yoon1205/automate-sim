package ast;

import java.util.List;

public class Room extends Decl {
    private final Name name;
    private final List<Device> devices;
    public Room(Name name, List<Device> devices) {
        this.devices = devices;
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }
    public Name getName() {
        return name;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

package model;

import java.util.HashMap;

public class Device {
    private final String name;
    private final HashMap<String, Property> props;

    public Device(String name) {
        this.name = name;
        this.props = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Property getProp(String name) {
        return props.get(name);
    }

    public void addProp(Property prop) {
        props.put(prop.getName(), prop);
    }
}

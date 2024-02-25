package model.context;

import ast.Type;
import model.Device;

import java.util.HashMap;

public class Context {
    private static final HashMap<String, Device> devices = new HashMap<>();
    private static final HashMap<String, Type> types = new HashMap<>();

    public static void addType(Type type) {types.put(type.getName().getText(), type); }




    public static void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public static Device getDevice(String name) {
        return devices.get(name);
    }

    public static HashMap<String, Device> getDevices() {
        return devices;
    }

    public static HashMap<String, Type> getTypes() {
        return types;
    }


    public static void clear() {
        devices.clear();
    }
}

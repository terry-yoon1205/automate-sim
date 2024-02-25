package model.context;

import model.Device;

import java.util.HashMap;

public class Context {
    private static final HashMap<String, Device> devices = new HashMap<>();


    public static void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public static Device getDevice(String name) {
        return devices.get(name);
    }

    public static HashMap<String, Device> getDevices() {
        return devices;
    }


    public static void clear() {
        devices.clear();
    }
}

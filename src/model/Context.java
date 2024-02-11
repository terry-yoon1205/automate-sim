package model;

import java.util.HashMap;
import java.util.Set;

public class Context {
    private static final HashMap<String, Set<Device>> rooms = new HashMap<>();
    private static final HashMap<String, Device> devices = new HashMap<>();
    private static final StringBuffer errors = new StringBuffer(); // dynamic errors

    public static void addRoom(String name, Set<Device> devices) {
        rooms.put(name, devices);
    }

    public static Set<Device> getRoom(String name) {
        return rooms.get(name);
    }

    public static void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public static Device getDevice(String name) {
        return devices.get(name);
    }

    public static void appendError(String error) {
        errors.append(error).append("\n");
    }

    public static void printErrors() {
        System.out.println(errors);
        errors.setLength(0);
    }
}

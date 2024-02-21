package model;

import java.util.HashMap;
import java.util.Set;

public class Context {
    private static final HashMap<String, Set<String>> rooms = new HashMap<>();
    private static final HashMap<String, Device> devices = new HashMap<>();
    private static final HashMap<String, Action> actions = new HashMap<>();

    private static final StringBuffer errors = new StringBuffer(); // dynamic errors

    public static HashMap<String, Set<String>> getRooms() {
        return rooms;
    }

    public static HashMap<String, Device> getDevices() {
        return devices;
    }

    public static HashMap<String, Action> getActions() {
        return actions;
    }

    public static void addRoom(String name, Set<String> deviceNames) {
        rooms.put(name, deviceNames);
    }

    public static Set<String> getRoomDevices(String name) {
        return rooms.get(name);
    }

    public static void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public static Device getDevice(String name) {
        return devices.get(name);
    }

    public static void addAction(String name, Action a) {
        actions.put(name, a);
    }

    public static Action getAction(String name) {
        return actions.get(name);
    }

    public static void appendError(String error) {
        errors.append(error).append("\n");
    }

    public static void printErrors() {
        System.out.println(errors);
        errors.setLength(0);
    }
}

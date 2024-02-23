package model.context;

import model.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// extra context that is not necessary for the final output, but can be used for testing.
public class TestContext {
    private static final HashMap<String, Set<String>> rooms = new HashMap<>();
    private static final HashMap<String, Action> actions = new HashMap<>();
    private static final List<String> prints = new ArrayList<>();


    public static HashMap<String, Set<String>> getRooms() {
        return rooms;
    }

    public static void addRoom(String name, Set<String> deviceNames) {
        rooms.put(name, deviceNames);
    }

    public static Set<String> getRoomDevices(String name) {
        return rooms.get(name);
    }


    public static HashMap<String, Action> getActions() {
        return actions;
    }

    public static void addAction(String name, Action a) {
        actions.put(name, a);
    }

    public static Action getAction(String name) {
        return actions.get(name);
    }


    public static void print(String s) {
        prints.add(s);
        System.out.println(s);
    }

    public static List<String> getPrints() {
        return prints;
    }


    public static void clear() {
        rooms.clear();
        actions.clear();
        prints.clear();
    }
}

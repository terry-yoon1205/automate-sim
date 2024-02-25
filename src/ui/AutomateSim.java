package ui;

import ast.PropType;
import ast.Type;
import model.*;
import model.context.Context;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


// final output of evaluation. the program can be run by calling run()
public class AutomateSim {
    private final Scanner input = new Scanner(System.in);
    HashMap<String, Device> devices;
    HashMap<String, Type> propTypes;

    public AutomateSim() {
        devices = Context.getDevices();
        propTypes = Context.getTypes();


    }

    private void displayTypes() {
        System.out.println("Available Types:");
        for (String typeName : propTypes.keySet()) {
            Type type = propTypes.get(typeName);
            System.out.println("- " + typeName);
            System.out.println("  Properties:");
            for (PropType propType : type.getProperties()) {
                System.out.println("    - " + propType.getName() + " (" + propType.getClass().getSimpleName() + ")");
            }
            Type supertype = type.getSupertype();
            if (supertype != null) {
                System.out.println("  Supertype: " + supertype.getName());
            } else {
                System.out.println("  Supertype: None");
            }
        }
    }


    public void run() {
        System.out.println("Available commands: ");
        System.out.println("To change a property, type '!set <device name>.<property name>'");
        System.out.println("To see available types and their properties, type '!types'");
        System.out.println("To see properties of a device, type '!props <device name>'");
        System.out.println("Or type '!q' to quit.");

        if (devices.isEmpty()) {
            System.out.println("No devices found, please make sure devices have been added.");
        } else {
            System.out.print("Available Devices: ");
            System.out.print("[");
            for (String deviceName : devices.keySet()) {
                System.out.print(deviceName);
                if (!deviceName.equals(devices.keySet().toArray()[devices.size() - 1])) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }

        String in = input.nextLine().strip();
        if (in.equals("!q")) {
            return;
        } else if (in.equals("!types")) {
            displayTypes();
        } else if (in.startsWith("!props")) {
            String[] parts = in.split(" ");
            if (parts.length == 2) {
                String deviceName = parts[1].strip();
                Device device = devices.get(deviceName);
                if (device != null) {
                    System.out.println("Properties of device '" + deviceName + "':");
                    for (String propName : device.getProps()) {
                        System.out.println("- " + propName);
                    }
                } else {
                    System.out.println("Device '" + deviceName + "' not found.");
                }
            } else {
                System.out.println("Invalid command format. Please use '!props <device name>'.");
            }
        } else if (in.startsWith("!set")) {
            String[] parts = in.split(" ");
            if (parts.length == 2) {
                String[] subParts = parts[1].split("\\.");
                if (subParts.length == 2) {
                    String deviceName = subParts[0].strip();
                    String propName = subParts[1].strip();
                    Device device = devices.get(deviceName);
                    if (device != null) {
                        Property prop = device.getProp(propName);
                        if (prop != null) {
                            typePropValue(prop);
                        } else {
                            System.out.println("Property '" + propName + "' does not exist on device '" + deviceName + "'.");
                        }
                    } else {
                        System.out.println("Device '" + deviceName + "' not found.");
                    }
                } else {
                    System.out.println("Invalid command format. Please use '!set <device name>.<property name>'.");
                }
            } else {
                System.out.println("Invalid command format. Please use '!set <device name>.<property name>'.");
            }
        } else {
            System.out.println("Invalid command.");
        }

        run();
    }

    private Property typePropName(String in) {
        String[] names = in.split("\\.");

        if (names.length != 2) {
            System.out.println("Invalid syntax. Please try again. (<device name>.<property name>)");
            return typePropName(input.next());
        }

        String deviceName = names[0].strip();
        String propName = names[1].strip();

        Device device = Context.getDevice(deviceName);
        if (device == null) {
            String error = String.format("The device %s does not exist. Please try again. " +
                    "(<device name>.<property name>)", deviceName);
            System.out.println(error);
            return typePropName(input.next());
        }

        Property prop = device.getProp(propName);
        if (prop == null) {
            String error = String.format("The property %s does not exist on the device %s. Please try again. " +
                    "(<device name>.<property name>)", propName, deviceName);
            System.out.println(error);

            Set<String> props = device.getProps();
            System.out.println("Valid properties: ");
            System.out.print("[");

            for (String iterPropName : props) {
                System.out.print(iterPropName);
                if (!iterPropName.equals(props.toArray()[props.size() - 1])) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            return typePropName(input.next());
        }

        return prop;
    }

    private void typePropValue(Property prop) {
        System.out.println("What value would you like to change it to?");
        String in = input.next();

        if (!prop.mutate(in)) {
            System.out.println("The value is invalid. Please try again.");
            typePropValue(prop);
        }
    }
}
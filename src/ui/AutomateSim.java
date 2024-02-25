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
    private final HashMap<String, Device> devices = Context.getDevices();
    private final HashMap<String, Type> propTypes = Context.getTypes();

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

        System.out.println();
    }

    private void displayDeviceProps(String in) {
        String[] parts = in.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Please use '!props <device name>'.");
            return;
        }

        String deviceName = parts[1].strip();
        Device device = devices.get(deviceName);

        if (device == null) {
            System.out.println("Device '" + deviceName + "' not found.");
        } else {
            System.out.println("Properties of device '" + deviceName + "':");

            for (String propName : device.getProps()) {
                System.out.println("- " + propName);
            }
        }

        System.out.println();
    }

    private void setDeviceProp(String in) {
        String[] parts = in.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Please use '!set <device name>.<property name>'.");
            return;
        }

        String[] subParts = parts[1].split("\\.");
        if (subParts.length != 2) {
            System.out.println("Invalid command format. Please use '!set <device name>.<property name>'.");
            return;
        }

        String deviceName = subParts[0].strip();
        String propName = subParts[1].strip();
        Device device = devices.get(deviceName);

        if (device == null) {
            System.out.println("Device '" + deviceName + "' not found.");
            return;
        }

        Property prop = device.getProp(propName);

        if (prop == null) {
            System.out.println("Property '" + propName + "' does not exist on device '" + deviceName + "'.");
        } else {
            typePropValue(prop);
        }
    }

    public void run() {
        System.out.println("Available commands: ");
        System.out.println("To change a property, type '!set <device name>.<property name>'");
        System.out.println("To see available types and their properties, type '!types'");
        System.out.println("To see properties of a device, type '!props <device name>'");
        System.out.println("Or type '!q' to quit.\n");

        if (devices.isEmpty()) {
            System.out.println("No devices found, please make sure devices have been added.");
        } else {
            System.out.print("Available Devices: ");

            String names = String.join(", ", devices.keySet());
            System.out.println("[" + names + "]");
        }

        String in = input.nextLine().strip();
        if (in.equals("!q")) {
            return;
        } else if (in.equals("!types")) {
            displayTypes();
        } else if (in.startsWith("!props")) {
            displayDeviceProps(in);
        } else if (in.startsWith("!set")) {
            setDeviceProp(in);
        } else {
            System.out.println("Invalid command.");
        }

        run();
    }

    private void typePropValue(Property prop) {
        System.out.println("What value would you like to change it to?");
        String in = input.next();

        if (!prop.mutate(in)) {
            System.out.println("The value is invalid. Please try again.");
            typePropValue(prop);
        }

        System.out.println();
    }
}
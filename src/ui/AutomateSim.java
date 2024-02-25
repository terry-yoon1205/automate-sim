package ui;

import model.*;
import model.context.Context;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

// final output of evaluation. the program can be run by calling run()
public class AutomateSim {
    private final Scanner input = new Scanner(System.in);

    public void run() {
        System.out.println("What property would you like to change? (<device name>.<property name>)");
        HashMap<String, Device> devices = Context.getDevices();
        if(devices.isEmpty()){
            System.out.println("No devices found, please make sure devices have been added.");
        }else{
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

        System.out.println("Or type !q to quit.");

        String in = input.next();
        if (in.strip().equals("!q")) {
            return;
        }

        Property prop = typePropName(in);
        typePropValue(prop);

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
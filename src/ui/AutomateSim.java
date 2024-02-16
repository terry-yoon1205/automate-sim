package ui;

import model.*;

import java.util.Scanner;

// final output of evaluation. the program can be run by calling run()
public class AutomateSim {
    private Scanner input = new Scanner(System.in);

    public void run() {
        System.out.println("What property would you like to change? (<device name>.<property name>)");
        System.out.println("Or type !q to quit at any point.");
        Property prop = typePropName();

        System.out.println("What value would you like to change it to?");
        typePropValue(prop);

        run();
    }

    private Property typePropName() {
        String in = input.next();
        if (in.strip().equals("!q")) {
            System.exit(0);
        }

        String[] names = in.split("\\.");

        if (names.length != 2) {
            System.out.println("Invalid syntax. Please try again. (<device name>.<property name>)");
            return typePropName();
        }

        String deviceName = names[0].strip();
        String propName = names[1].strip();

        Device device = Context.getDevice(deviceName);
        if (device == null) {
            String error = String.format("The device %s does not exist. Please try again. " +
                    "(<device name>.<property name>)", deviceName);
            System.out.println(error);
            return typePropName();
        }

        Property prop = device.getProp(propName);
        if (prop == null) {
            String error = String.format("The property %s does not exist on the device %s. Please try again. " +
                    "(<device name>.<property name>)", propName, deviceName);
            System.out.println(error);
            return typePropName();
        }

        return prop;
    }

    private void typePropValue(Property prop) {
        String in = input.next();
        if (in.strip().equals("!q")) {
            System.exit(0);
        }

        if (!prop.mutate(in)) {
            System.out.println("The value is invalid. Please try again.");
            typePropValue(prop);
        }
    }
}

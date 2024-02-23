package ui;

import model.*;
import model.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

// final output of evaluation. the program can be run by calling run()
public class AutomateSim {
    private Scanner input;

    // writes to our output set in the constructor for us
    private PrintStream printer;

    public AutomateSim() {
        input = new Scanner(System.in);
        printer = System.out;
    }

    // provide input using example here.
    // https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
    public AutomateSim(InputStream in, ByteArrayOutputStream out) {
        input = new Scanner(in);
        printer = new PrintStream(out);
    }

    public void run() {
        output("What property would you like to change? (<device name>.<property name>)");
        output("Or type !q to quit at any point.");
        Property prop = typePropName();

        if (prop == null) {
            output("Ending program.");
            return;
        }

        output("What value would you like to change it to?");
        typePropValue(prop);


    }

    private void output(String s) {
        printer.println(s);
    }


    private Property typePropName() {
        String in = input.next();
        if (in.strip().equals("!q")) {
            return null;
        }

        if (in.length() == 0) {
            output("No input found. Quitting");
            return null;
        }

        String[] names = in.split("\\.");

        if (names.length != 2) {
            output("Invalid syntax. Please try again. (<device name>.<property name>)");
            return typePropName();
        }

        String deviceName = names[0].strip();
        String propName = names[1].strip();

        Device device = Context.getDevice(deviceName);
        if (device == null) {
            String error = String.format("The device %s does not exist. Please try again. " +
                    "(<device name>.<property name>)", deviceName);
            output(error);
            return typePropName();
        }

        Property prop = device.getProp(propName);
        if (prop == null) {
            String error = String.format("The property %s does not exist on the device %s. Please try again. " +
                    "(<device name>.<property name>)", propName, deviceName);
            output(error);
            return typePropName();
        }

        return prop;
    }

    private void typePropValue(Property prop) {
        String in = input.next();
        if (in.strip().equals("!q")) {
            return;
        }

        if (!prop.mutate(in)) {
            output("The value is invalid. Please try again.");
            typePropValue(prop);
        }
    }
}

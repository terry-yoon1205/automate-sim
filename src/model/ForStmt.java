package model;

import model.interfaces.Parent;

import java.util.ArrayList;
import java.util.List;

public class ForStmt extends Stmt { // composite of the composite pattern
    private final List<String> devices;

    public ForStmt() {
        super(null, null);
        this.devices = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void addDevice(String device) {
        devices.add(device);
    }

    public Stmt updateDevices(Stmt s, String deviceName) {
        s.setDevice(deviceName);

        if (s.getChildren() == null) {
            return s;
        }

        for (Stmt childStmt: s.getChildren()) {
            updateDevices(childStmt, deviceName);
        }

        return s;
    }

    @Override
    public void execute() {
        for (String deviceName : devices) {
            for (Stmt s : children) {
                updateDevices(s, deviceName).execute();
            }
        }
    }
}

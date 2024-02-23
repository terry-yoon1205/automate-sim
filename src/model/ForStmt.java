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

    @Override
    public void execute() {
        for (String deviceName : devices) {
            for (Stmt s : children) {
                s.setDevice(deviceName);
                s.execute();
            }
        }
    }
}

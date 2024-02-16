package model;

import java.util.ArrayList;
import java.util.List;

public class ForStmt extends Stmt { // composite of the composite pattern
    private final List<Stmt> children;
    private final List<String> devices;

    public ForStmt() {
        super(null, null);
        this.children = new ArrayList<>();
        this.devices = new ArrayList<>();
    }

    public void addStmt(Stmt stmt) {
        children.add(stmt);
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

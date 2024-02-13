package model;

public class ForStmt extends Stmt { // composite of the composite pattern
    private final String room;

    public ForStmt(String deviceType, String room) {
        super(deviceType, null); // loop through this device type
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public void execute() {
        // TODO
    }
}

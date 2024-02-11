package model;

public class ForStmt extends Stmt { // composite of the composite pattern
    private final String room;

    public ForStmt(String device, String room) {
        super(device, null);
        this.room = room;
    }

    @Override
    public void execute() {
        // TODO
    }
}

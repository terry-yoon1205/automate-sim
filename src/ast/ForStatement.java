package ast;

import java.util.List;

public class ForStatement extends Statement {
    private final Var name;
    private final Type type;
    private final Room room;
    private final List<Statement> statements;

    public ForStatement(Var name, Type type, Room room, List<Statement> statements) {
        this.name = name;
        this.type = type;
        this.room = room;
        this.statements = statements;
    }

    public Var getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Room getRoom() {
        return room;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

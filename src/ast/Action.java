package ast;

import java.util.List;
import java.util.Objects;

public class Action extends Decl {
    private final Var name;
    private final List<DeviceProp> triggers;
    private final List<Statement> statements;

    public Action(Var name, List<DeviceProp> triggers, List<Statement> statements) {
        this.triggers = triggers;
        this.statements = statements;
        this.name = name;
    }

    public List<DeviceProp> getTriggers() {
        return triggers;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public Var getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action action)) return false;
        return Objects.equals(name, action.name) && Objects.equals(triggers, action.triggers) && Objects.equals(statements, action.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, triggers, statements);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

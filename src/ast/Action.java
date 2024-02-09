package ast;

import java.util.List;

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
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

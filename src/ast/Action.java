package ast;

import java.util.List;

public class Action extends Decl {
    private final Name name;
    private final List<Statement> statements;
    public Action(Name name, List<Statement> statements) {
        this.statements = statements;
        this.name = name;
    }

    public List<Statement> getStatements() {
        return statements;
    }
    public Name getName() {
        return name;
    }
    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return null;
    }
}

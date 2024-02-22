package ast;

import java.util.List;
import java.util.Objects;

public class Program extends Node{
    private final List<Decl> decls;

    public List<Decl> getStatements() {
        return decls;
    }

    public Program(List<Decl> decls) {
        this.decls = decls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program program)) return false;
        return Objects.equals(decls, program.decls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decls);
    }

    @Override
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}
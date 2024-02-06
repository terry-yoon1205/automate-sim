package ast;

import java.util.List;

public class Program extends Node{
    private final List<Decl> decls;

    public List<Decl> getStatements() {
        return decls;
    }

    public Program(List<Decl> decls) {
        this.decls = decls;
    }

    @Override
    public <C,T> T accept(C context, Visitor<C,T> v) {
        return v.visit(context, this);
    }
}
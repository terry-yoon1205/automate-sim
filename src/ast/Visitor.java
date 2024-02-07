package ast;

public interface Visitor<C,T> {
    // Recall: one visit method per concrete AST node subclass
    T visit(C context, Program p);
    T visit(C context, Action p);
    T visit(C context, Decl p);
    T visit(C context, Var p);
    T visit(C context, Room p);
    T visit(C context, Type p);
    T visit(C context, NumberVal p);
    T visit(C context, StringVal p);
    T visit(C context, EnumVal p);
}
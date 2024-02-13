package ast;

public interface Visitor<C, T> {
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

    T visit(C context, NumberType p);

    T visit(C context, StringType p);

    T visit(C context, EnumType p);

    T visit(C context, DeviceProp p);
    T visit(C context, Device p);

    T visit(C context, SetStatement p);

    T visit(C context, IfStatement p);

    T visit(C context, ForStatement p);
}
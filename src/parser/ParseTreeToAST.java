package parser;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class ParseTreeToAST extends AutomateSimParserBaseVisitor<Node> {
    @Override
    public Program visitProgram(AutomateSimParser.ProgramContext ctx) {
        List<Decl> decls = new ArrayList<>();
        for (AutomateSimParser.DeclContext d : ctx.decl()) {
            decls.add((Decl) d.accept(this));
        }

        return new Program(decls);
    }

    @Override
    public Room visitRoom(AutomateSimParser.RoomContext ctx) {
        List<Device> devices = new ArrayList<>();


        for (AutomateSimParser.DeviceContext r : ctx.device()) {
            devices.add((Device) r.accept(this));

        }
        return new Room((Var) ctx.VAR().accept(this), devices);
        return null;
    }

    @Override
    public SetStatement visitSet(AutomateSimParser.SetContext ctx) {
//        return new SetStatement(
//                visitDevice_prop(ctx.device_prop()),
//                ctx.
//
//        )
        return null;
    }

    @Override
    public Type visitType(AutomateSimParser.TypeContext ctx) {
        return null;
    }
}

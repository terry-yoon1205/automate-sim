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
    }

    @Override
    public SetStatement visitSet(AutomateSimParser.SetContext ctx) {
//        DeviceProp dp = (DeviceProp) ctx.device_prop().accept(this);
//        if (ctx.VAR(0) != null) {
//            Var device = (Var) ctx.VAR(0).accept(this);
//            // TODO: CHECK
//
//            if (ctx.VAR(1) != null) {
//                Var prop = (Var) ctx.VAR(1).accept(this);
//                // TODO: CHECK
//                DeviceProp dp2 = new DeviceProp(
//                        device,
//                        prop);
//
//                return new SetStatement(dp, dp2);
//            } else {
//                EnumVal ev = new EnumVal((Var) ctx.VAR(0).accept(this), ));
//                return new SetStatement(dp, ev);
//            }
//
//
//        } else if (ctx.NUM() != null) {
//            return new SetStatement(
//                    dp,
//                    (NumberVal) ctx.NUM());
//        }
        return null;
    }

    @Override
    public Type visitType(AutomateSimParser.TypeContext ctx) {
        List<PropType> properties = new ArrayList<>();

        for (AutomateSimParser.PropertyContext p : ctx.property()) {
            properties.add((PropType) p.accept(this));
        }
        Type type = null;
        // TODO: need to contruct inheritance tree

        return new Type((Var) ctx.VAR(0).accept(this),
                type,
                properties);
    }
}

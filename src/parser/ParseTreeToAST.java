package parser;

import ast.*;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ParseTreeToAST extends AutomateSimParserBaseVisitor<Node> {
    public Action visitAction(AutomateSimParser.ActionContext ctx) {
        if (ctx.device_prop().isEmpty()) {
            System.out.println("Please provide what device would trigger the action.");
            return null;
        }
        if (ctx.statement().isEmpty()) {
            System.out.println("Please provide what you want the action do after it is triggered.");
            return null;
        }

        Var name = new Var(ctx.VAR().getText());
        List<DeviceProp> triggers = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        for (AutomateSimParser.Device_propContext dev_prop : ctx.device_prop()) {
            triggers.add(visitDevice_prop(dev_prop));
        }
        for (AutomateSimParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visitStatement(statement));
        }
        return new Action(name, triggers, statements);
    }

    public Decl visitDecl(AutomateSimParser.DeclContext ctx) {
        return (Decl) visitChildren(ctx);
    }

    public Device visitDevice(AutomateSimParser.DeviceContext ctx) {
        if (ctx.arg() != null) {
            Var name = new Var(ctx.VAR(0).getText());
            //TODO: fix check type is existed
//            Type type = visitType(ctx.VAR(1).getText());
            List<PropVal> values = new ArrayList<>();

            for (int i = 0; i < ctx.arg().size(); i++) {
                values.add((PropVal) visitArg(ctx.arg(i)));
            }
//            return new Device(name, type, values);
        } else {
            System.out.println("Please provide default device value");
        }

        return null;
    }

    public DeviceProp visitDevice_prop(AutomateSimParser.Device_propContext ctx) {
        if (ctx.VAR(1) != null) {
            Var device = new Var(ctx.VAR(0).getText());
            Var prop = new Var(ctx.VAR(1).getText());
            return new DeviceProp(device, prop);
        } else {
            System.out.println("Please provide device's property");
        }
        return null;
    }

    public EnumType visitEnum(AutomateSimParser.EnumContext ctx) {
        if (!ctx.VAR(1).getText().equals("<missing VAR>")) {
            List<Var> states = new ArrayList<>();
            Var name = new Var(ctx.VAR(0).getText());

            for(int i = 1; i < ctx.VAR().size(); ++i) {
                System.out.println(ctx.VAR(i).getText());
                states.add(new Var(ctx.VAR(i).getText()));
            }
            return new EnumType(name, states);
        } else {
            System.out.println("Please give a valid Enum type (ON, OFF, dimmed, etc)");
            return null;
        }
    }

    public ForStatement visitFor(AutomateSimParser.ForContext ctx) {
//        if (ctx.statement() != null) {
            Var name = new Var(ctx.VAR(0).getText());
            // TODO: fix check type and room are exist
//        Type type = visitType(ctx.VAR(1).getText());
//        Room room = visitRoom(ctx.VAR(2).getText());
            List<Statement> statements = new ArrayList<>();
            for (AutomateSimParser.StatementContext statement : ctx.statement()) {
                statements.add((Statement) visitStatement(statement));
            }
//        return new ForStatement(name, type, room, statements);
//        } else {
//
//        }

        return null;
    }

    public IfStatement visitIf(AutomateSimParser.IfContext ctx) {
//        if (ctx.statement() != null) {
            List<Statement> statements = new ArrayList<>();
            for (AutomateSimParser.StatementContext statement : ctx.statement()) {
                statements.add((Statement) visitStatement(statement));
            }
            return new IfStatement(visitDevice_prop(ctx.device_prop()), (PropVal) visitArg(ctx.arg()), statements);
//        } else {
//            return new IfStatement(visitDevice_prop(ctx.device_prop()), (PropVal) visitArg(ctx.arg()), Collections.emptyList());
//        }

    }

    public NumberType visitNumber(AutomateSimParser.NumberContext ctx) {
        try {
            if (ctx.NUM().size() > 2) {
                throw new NumberFormatException();
            }
            Var name = new Var(ctx.VAR().getText());
            int min = Integer.parseInt(ctx.NUM(0).getText());
            int max = Integer.parseInt(ctx.NUM(1).getText());
            if (max < min) {
                throw new RuntimeException("The first number is the minimum value, the second one is the maximum value.");
            }
            return new NumberType(name, min, max);
        } catch (NumberFormatException e){
            System.out.println("Please provide two valid integer separate with an comma");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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


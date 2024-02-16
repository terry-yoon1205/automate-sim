package parser;

import ast.*;

import ast.Action;
import ast.Device;
import ast.DeviceProp;
import ast.EnumType;
import ast.IfStatement;
import ast.Node;
import ast.NumberType;
import ast.Var;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseTreeToAST extends AutomateSimParserBaseVisitor<Node> {
    public ParseTreeToAST() {
    }

    public Action visitAction(AutomateSimParser.ActionContext ctx) {
        if (ctx != null) {
            new Var(ctx.VAR().getText());
            List<DeviceProp> triggers = new ArrayList();
            Iterator var4 = ctx.device_prop().iterator();

            while(var4.hasNext()) {
                AutomateSimParser.Device_propContext dev_prop = (AutomateSimParser.Device_propContext)var4.next();
                Var device = new Var(dev_prop.VAR(0).getText());
                Var property = new Var(dev_prop.VAR(1).getText());
                DeviceProp deviceProp = new DeviceProp(device, property);
                triggers.add(deviceProp);
            }

            AutomateSimParser.StatementContext var9;
            for(var4 = ctx.statement().iterator(); var4.hasNext(); var9 = (AutomateSimParser.StatementContext)var4.next()) {
            }

            System.out.println(((DeviceProp)triggers.get(0)).getDevice());
        }

        return null;
    }

    public Device visitDevice(AutomateSimParser.DeviceContext ctx) {
        if (ctx != null) {
            Var name = new Var(ctx.VAR(0).getText());
            System.out.println(name);
        } else {
            System.out.println("Please give two valid numbers separate with an comma");
        }

        return null;
    }

    public DeviceProp visitDevice_prop(AutomateSimParser.Device_propContext ctx) {
        if (ctx != null) {
            Var device = new Var(ctx.VAR(0).getText());
            Var prop = new Var(ctx.VAR(1).getText());
            return new DeviceProp(device, prop);
        } else {
            System.out.println("Please give two valid numbers separate with an comma");
            return null;
        }
    }

    public EnumType visitEnum(AutomateSimParser.EnumContext ctx) {
        if (ctx == null) {
            System.out.println("Please give valid Enum type (ON, OFF");
            return null;
        } else {
            List<Var> states = new ArrayList();
            Var name = new Var(ctx.VAR(0).getText());

            for(int i = 1; i < ctx.VAR().size(); ++i) {
                states.add(new Var(ctx.VAR(i).getText()));
            }

            return new EnumType(name, states);
        }
    }

    public IfStatement visitIf(AutomateSimParser.IfContext ctx) {
        if (ctx != null) {
        }

        return null;
    }

    public NumberType visitNumber(AutomateSimParser.NumberContext ctx) {
        if (ctx.NUMBER() != null) {
            Var name = new Var(ctx.VAR().getText());
            int min = Integer.parseInt(ctx.NUM(0).getText());
            int max = Integer.parseInt(ctx.NUM(1).getText());
            System.out.println(name);
            return new NumberType(name, min, max);
        } else {
            System.out.println("Please give two valid numbers separate with an comma");
            return null;
        }
    }
}


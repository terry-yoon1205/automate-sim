package parser;

import ast.*;

import java.util.*;

public class ParseTreeToAST extends AutomateSimParserBaseVisitor<Node> {
    Set<Room> addedRoom = new HashSet<>();
    Set<Device> addedDevice = new HashSet<>();
    Set<Type> addedType = new HashSet<>();

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
            Type type = visitType(new Var(ctx.VAR(1).getText()));
            if (type == null) {
                System.out.println("The type of device does not exist");
                return null;
            }

            List<PropVal> values = new ArrayList<>();
            for (int i = 0; i < ctx.arg().size(); i++) {
                values.add((PropVal) visitArg(ctx.arg(i)));
            }
            Device createdDevice = new Device(name, type, values);
            addedDevice.add(createdDevice);
            return new Device(name, type, values);
        } else {
            System.out.println("Please provide default device value");
        }
        return null;
    }

    public Device visitDevice(Var var) {
        return addedDevice.stream()
                .filter(obj -> obj.getName().equals(var))
                .findFirst()
                .orElse(null);
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
        Type type = visitType(new Var(ctx.VAR(1).getText()));
        Room room = visitRoom(new Var(ctx.VAR(2).getText()));
        if (type == null || room == null) {
            System.out.println("The type or the room provided do not exists");
            return null;
        }
        List<Statement> statements = new ArrayList<>();
        for (AutomateSimParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visitStatement(statement));
        }
        return new ForStatement(name, type, room, statements);
//        } else {
//
//        }
//        return null;
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

    public Room visitRoom(Var var) {
        return addedRoom.stream()
                .filter(obj -> obj.getName().equals(var))
                .findFirst()
                .orElse(null);

    }

    public Type visitType(Var var) {
        return addedType.stream()
                .filter(obj -> obj.getName().equals(var))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PropType visitProperty(AutomateSimParser.PropertyContext ctx) {
        return (PropType) visitChildren(ctx);
    }

    @Override
    public PropVal visitArg(AutomateSimParser.ArgContext ctx) {
        //TODO
//        if (ctx.VAR() != null) {
//            return new EnumVal()
//        } else if (ctx.NUM() != null) {
//
//        } else if (ctx.STR() != null) {
//
//        } else {
//            throw new RuntimeException("Unknown invalid input");
//        }
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
        Var roomName = new Var(ctx.VAR().getText());

        if (visitRoom(roomName) != null) {
            throw new RuntimeException("Room " + roomName.getText() + " is already defined");
        }

        for (AutomateSimParser.DeviceContext r : ctx.device()) {
            devices.add((Device) r.accept(this));
        }
        Room createdRoom = new Room(roomName, devices);
        addedRoom.add(createdRoom);

        return createdRoom;
    }

    private PropVal containsProperty(List<PropVal> deviceProperties, Var prop) {
        for (PropVal pv: deviceProperties) {
            if (pv.getVarName().equals(prop.getText())) {
                return pv;
            }
        }
        return null;
    }

    @Override
    public SetStatement visitSet(AutomateSimParser.SetContext ctx) {
        DeviceProp dp = (DeviceProp) ctx.device_prop().accept(this);
        // Check if device exists
        Device device = visitDevice(dp.getDevice());
        if (device == null) {
            throw new RuntimeException("Device " + dp.getDevice().getText() + " does not exist");
        }

        List<PropVal> dValues = device.getValues();
        // Check if the property of the device exists
        PropVal deviceProperty = containsProperty(dValues, dp.getProp());
        if (deviceProperty == null) {
            throw new RuntimeException("Device " + device.getName().getText() + " has no property " + dp.getProp().getText());
        }

        // set to device prop
        if (ctx.VAR(0) != null && ctx.VAR(1) != null) {
            Var deviceName = new Var(ctx.VAR(0).getText());
            Var prop = new Var(ctx.VAR(1).getText());

            Device device2 = visitDevice(deviceName);
            // Check if second device exists
            if (device2 == null) {
                throw new RuntimeException("Device " + dp.getDevice().getText() + " does not exist");
            }

            List<PropVal> d2Values = device2.getValues();
            // Check if second device has the property
            PropVal device2Property = containsProperty(d2Values, prop);
            if (device2Property == null) {
                throw new RuntimeException("Device " + device2.getName().getText() + " has no property " + prop.getText());
            }

            // check if both are same type
            if (!device2Property.getType().equals(deviceProperty.getType())) {
                throw new RuntimeException("Unexpected type for " + deviceProperty.getVarName());
            }

            DeviceProp dp2 = new DeviceProp(deviceName, prop);
            return new SetStatement(dp, dp2);
        // set to enum
        } else if (ctx.VAR(0) != null) {
            Var enumValue = new Var(ctx.VAR(0).getText());

            // Check if it is enum type
            if (!(deviceProperty.getType() instanceof EnumType)) {
                throw new RuntimeException(deviceProperty.getVarName() + " is not an enum");
            }

            EnumType dEnum = (EnumType) deviceProperty.getType();
            if (!dEnum.getStates().contains(enumValue)) {
                throw new RuntimeException(enumValue.getText() + " is an invalid stated for " + deviceProperty.getVarName());
            }

            EnumVal ev = new EnumVal(deviceProperty.getVarName(), enumValue, deviceProperty.getType());

            return new SetStatement(dp, ev);
        // set to string
        } else if (ctx.STR() != null) {
            StringVal string = new StringVal(dp.getProp().getText(), ctx.STR().getText(), deviceProperty.getType());

            return new SetStatement(dp, string);
        // set to number
        } else if (ctx.NUM() != null) {
            NumberVal num = new NumberVal(dp.getProp().getText(), ctx.NUM().getText(), deviceProperty.getType());
            return new SetStatement(dp, num);
        } else {
            throw new RuntimeException("Unknown invalid input");
        }
    }

    @Override
    public Type visitType(AutomateSimParser.TypeContext ctx) {
        Var typeName = new Var(ctx.VAR(0).getText());
        // Check if type is already defined
        if (visitType(typeName) != null) {
            throw new RuntimeException("Type " + typeName.getText() + " is already defined");
        }

        Var superTypeName = new Var(ctx.VAR(1).getText());
        // Check if the supertype exists
        Type superType = visitType(superTypeName);
        if (superType == null) {
            throw new RuntimeException("Type " + typeName.getText() + " is not defined");
        }

        // Get properties
        List<PropType> properties = new ArrayList<>();
        for (AutomateSimParser.PropertyContext p : ctx.property()) {
            properties.add((PropType) p.accept(this));
        }

        Type createdType = new Type(typeName, superType, properties);
        addedType.add(createdType);

        return createdType;
    }


    @Override
    public StringType visitString(AutomateSimParser.StringContext ctx) {
        StringType st = new StringType(new Var(ctx.VAR().getText()));
        return st;
    }
}


package parser;

import ast.*;
import exceptions.ParserException;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;

import java.util.*;

public class ParseTreeToAST extends AutomateSimParserBaseVisitor<Node> {
    Set<Room> addedRoom = new HashSet<>();
    Set<Device> addedDevice = new HashSet<>();
    Set<Type> addedType = new HashSet<>();
    Set<Action> addedAction = new HashSet<>();

    public Action visitAction(AutomateSimParser.ActionContext ctx) {
        if (ctx.device_prop().isEmpty()) {
            throw new ParserException("Please provide what device would trigger the action.");
        }
//        if (ctx.statement().isEmpty()) {
//            System.out.println("Please provide what you want the action do after it is triggered.");
//            return null;
//        }
        if (ctx.VAR() instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid action name");
        }


        Var name = new Var(ctx.VAR().getText());
        if (getAction(name) != null) {
            throw new ParserException("Action " + name.getText() + " is already defined");

        }
        List<DeviceProp> triggers = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        for (AutomateSimParser.Device_propContext dev_prop : ctx.device_prop()) {
            DeviceProp dp = visitDevice_prop(dev_prop);
            if (triggers.contains(dp)) {
                throw new ParserException("Duplicate trigger " + dp.getDevice().getText() + "." + dp.getProp().getText() + " in action "  + name.getText());
            }
            triggers.add(visitDevice_prop(dev_prop));
        }
        for (AutomateSimParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visitStatement(statement));
        }
        Action a = new Action(name, triggers, statements);
        addedAction.add(a);
        return a;
    }

    public Decl visitDecl(AutomateSimParser.DeclContext ctx) {
        return (Decl) visitChildren(ctx);
    }

    public Device visitDevice(AutomateSimParser.DeviceContext ctx) {
        if (ctx.VAR(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid device name");
        }
        Var name = new Var(ctx.VAR(0).getText());
        Device device = visitDevice(name);
        if (device != null) {
            throw new ParserException("Device " + name.getText() + " is already defined");
        }

        if (ctx.VAR(1) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid type name");
        }

        Var typeName = new Var(ctx.VAR(1).getText());
        Type type = visitType(typeName);
        if (type == null) {
            throw new ParserException("Device " + typeName.getText() + " does not exist");
        }


        List<PropType> typeProperties = type.getProperties();

        if (typeProperties.size() != ctx.arg().size()) {
            throw new ParserException(name.getText() + " expected " + typeProperties.size() + " arguments, got " + ctx.arg().size());
        }

        List<PropVal> values = new ArrayList<>();
        for (int i = 0; i < ctx.arg().size(); i++) {
            values.add((PropVal) visitArg(ctx.arg(i), typeProperties.get(i)));
        }
        Device createdDevice = new Device(name, type, values);
        addedDevice.add(createdDevice);
        return new Device(name, type, values);
    }

    public Device visitDevice(Var var) {
        return addedDevice.stream()
                .filter(obj -> obj.getName().equals(var))
                .findFirst()
                .orElse(null);
    }

    public DeviceProp visitDevice_prop(AutomateSimParser.Device_propContext ctx) {
        if (ctx.VAR(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid device name");
        }
        if (ctx.VAR(1) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid property name");
        }

        Var deviceName = new Var(ctx.VAR(0).getText());
        Var propName = new Var(ctx.VAR(1).getText());

        Device device = visitDevice(deviceName);
        if (device == null) {
            throw new ParserException("Device " + deviceName.getText() + " does not exist");
        }

        List<PropVal> dValues = device.getValues();
        // Check if the property of the device exists
        PropVal deviceProperty = containsPropertyVal(dValues, propName);
        if (deviceProperty == null) {
            throw new ParserException("Device " + device.getName().getText() + " has no property " + propName.getText());
        }

        return new DeviceProp(deviceName, propName);
    }

    public EnumType visitEnum(AutomateSimParser.EnumContext ctx) {
        if (ctx.VAR(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid enum name");
        }
        if (ctx.VAR(1) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid enum value");
        }
        List<Var> states = new ArrayList<>();
        Set<String> names = new HashSet<>();
        Var name = new Var(ctx.VAR(0).getText());

        for(int i = 1; i < ctx.VAR().size(); ++i) {
//            System.out.println(ctx.VAR(i).getText());
            if (names.contains(ctx.VAR(i).getText())) {
                throw new ParserException("Duplicate enum value");
            }
            names.add(ctx.VAR(i).getText());
            states.add(new Var(ctx.VAR(i).getText()));
        }
        return new EnumType(name, states);
    }

    public ForStatement visitFor(AutomateSimParser.ForContext ctx) {
//        if (ctx.statement() != null) {
        if (ctx.VAR(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid variable name");
        }
        Var name = new Var(ctx.VAR(0).getText());
        if (ctx.VAR(1) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid type name");
        }
        Type type = visitType(new Var(ctx.VAR(1).getText()));

        if (ctx.VAR(2) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid room name");
        }
        Room room = visitRoom(new Var(ctx.VAR(2).getText()));

        if (type == null) {
            throw new ParserException("Type " + ctx.VAR(1).getText() + " does not exists");
        }
        if (room == null) {
            throw new ParserException("Room " + ctx.VAR(2).getText() + " does not exists");
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
        DeviceProp dp = (DeviceProp) ctx.device_prop().accept(this);
        // Check if device exists
        Device device = visitDevice(dp.getDevice());
        if (device == null) {
            throw new ParserException("Device " + dp.getDevice().getText() + " does not exist");
        }

        List<PropVal> dValues = device.getValues();
        // Check if the property of the device exists
        PropVal deviceProperty = containsPropertyVal(dValues, dp.getProp());
        if (deviceProperty == null) {
            throw new ParserException("Device " + device.getName().getText() + " has no property " + dp.getProp().getText());
        }

        List<Statement> statements = new ArrayList<>();
        for (AutomateSimParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visitStatement(statement));
        }
        return new IfStatement(dp, (PropVal) visitArg(ctx.arg(), deviceProperty.getType()), statements);
//        } else {
//            return new IfStatement(visitDevice_prop(ctx.device_prop()), (PropVal) visitArg(ctx.arg()), Collections.emptyList());
//        }

    }

    public NumberType visitNumber(AutomateSimParser.NumberContext ctx) {
        if (ctx.NUM(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid first number");
        }
        if (ctx.NUM(1) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid second number");
        }
        try {
            Var name = new Var(ctx.VAR().getText());
            int min = Integer.parseInt(ctx.NUM(0).getText());
            int max = Integer.parseInt(ctx.NUM(1).getText());
            if (max < min) {
                throw new ParserException("The first number is the minimum value, the second one is the maximum value.");
            }
            return new NumberType(name, min, max);
        } catch (NumberFormatException e){
            throw new ParserException("Please provide two valid integer separate with an comma");
        }
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

    public Action getAction(Var var) {
        return addedAction.stream()
                .filter(obj -> obj.getName().equals(var))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PropType visitProperty(AutomateSimParser.PropertyContext ctx) {
        return (PropType) visitChildren(ctx);
    }

    public PropVal visitArg(AutomateSimParser.ArgContext ctx, PropType propType) {
        // TODO: CHeck nulls
        if (ctx.VAR() != null) {
            if (!(propType instanceof EnumType)) {
                throw new ParserException("Did not expect enum argument " + ctx.VAR().getText());
            }
            Var value = new Var(ctx.VAR().getText());
            if (!((EnumType) propType).getStates().contains(value)) {
                throw new ParserException(value.getText() + " is not a valid state for " + propType.getName().getText());
            }
            return new EnumVal(propType.getName().getText(), value, propType);
        } else if (ctx.NUM() != null) {
            if (!(propType instanceof NumberType)) {
                throw new ParserException("Did not expect number argument " + ctx.NUM().getText());
            }
            int min = ((NumberType) propType).getMin();
            int max = ((NumberType) propType).getMax();
            int value = Integer.parseInt(ctx.NUM().getText());
            if (!((min <= value) && (value <= max))) {
                throw new ParserException(value + " out of bounds " + "[" + min + ", " + max + "]");
            }
            return new NumberVal(propType.getName().getText(), ctx.NUM().getText(), propType);
        } else if (ctx.STR() != null) {
            if (!(propType instanceof StringType)) {
                throw new ParserException("Did not expect string argument " + ctx.STR().getText());
            }
            return new StringVal(propType.getName().getText(), ctx.STR().getText().replace("\"", ""), propType);
        } else {
            throw new ParserException("Unknown invalid input");
        }
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
        if (ctx.VAR() instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid room name");
        }
        Var roomName = new Var(ctx.VAR().getText());

        if (visitRoom(roomName) != null) {
            throw new ParserException("Room " + roomName.getText() + " is already defined");
        }

        for (AutomateSimParser.DeviceContext r : ctx.device()) {
            devices.add((Device) r.accept(this));
        }
        Room createdRoom = new Room(roomName, devices);
        addedRoom.add(createdRoom);

        return createdRoom;
    }

    private PropVal containsPropertyVal(List<PropVal> deviceProperties, Var prop) {
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
            throw new ParserException("Device " + dp.getDevice().getText() + " does not exist");
        }

        List<PropVal> dValues = device.getValues();
        // Check if the property of the device exists
        PropVal deviceProperty = containsPropertyVal(dValues, dp.getProp());
        if (deviceProperty == null) {
            throw new ParserException("Device " + device.getName().getText() + " has no property " + dp.getProp().getText());
        }

        //TODO check null check static

        // set to device prop
        if (ctx.VAR(0) != null && ctx.VAR(1) != null) {
            Var deviceName = new Var(ctx.VAR(0).getText());
            Var prop = new Var(ctx.VAR(1).getText());

            Device device2 = visitDevice(deviceName);
            // Check if second device exists
            if (device2 == null) {
                throw new ParserException("Device " + dp.getDevice().getText() + " does not exist");
            }

            List<PropVal> d2Values = device2.getValues();
            // Check if second device has the property
            PropVal device2Property = containsPropertyVal(d2Values, prop);
            if (device2Property == null) {
                throw new ParserException("Device " + device2.getName().getText() + " has no property " + prop.getText());
            }

            // check if both are same type
            if (!device2Property.getType().equals(deviceProperty.getType())) {
                throw new ParserException("Unexpected type for " + deviceProperty.getVarName());
            }

            DeviceProp dp2 = new DeviceProp(deviceName, prop);
            return new SetStatement(dp, dp2);
        // set to enum
        } else if (ctx.VAR(0) != null) {
            Var enumValue = new Var(ctx.VAR(0).getText());

            // Check if it is enum type
            if (!(deviceProperty.getType() instanceof EnumType)) {
                throw new ParserException(deviceProperty.getVarName() + " is not an enum");
            }

            EnumType dEnum = (EnumType) deviceProperty.getType();
            if (!dEnum.getStates().contains(enumValue)) {
                throw new ParserException(enumValue.getText() + " is an invalid state for " + deviceProperty.getVarName());
            }

            EnumVal ev = new EnumVal(deviceProperty.getVarName(), enumValue, deviceProperty.getType());

            return new SetStatement(dp, ev);
        // set to string
        } else if (ctx.STR() != null) {
            if (!(deviceProperty.getType() instanceof StringType)) {
                throw new ParserException(deviceProperty.getVarName() + " is not a string");
            }
            StringVal string = new StringVal(dp.getProp().getText(), ctx.STR().getText().replace("\"", ""), deviceProperty.getType());

            return new SetStatement(dp, string);
        // set to number
        } else if (ctx.NUM() != null) {
            if (!(deviceProperty.getType() instanceof NumberType)) {
                throw new ParserException(deviceProperty.getVarName() + " is not an number");
            }
            NumberVal num = new NumberVal(dp.getProp().getText(), ctx.NUM().getText(), deviceProperty.getType());
            return new SetStatement(dp, num);
        } else {
            throw new ParserException("Invalid set value");
        }
    }

    @Override
    public Type visitType(AutomateSimParser.TypeContext ctx) {
        if (ctx.VAR(0) instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid type name");
        }
        Var typeName = new Var(ctx.VAR(0).getText());
        // Check if type is already defined
        if (visitType(typeName) != null) {
            throw new ParserException("Type " + typeName.getText() + " is already defined");
        }

        if (ctx.property().isEmpty()) {
            throw new ParserException("Provide properties for " + typeName.getText());
        }
        List<PropType> properties = new ArrayList<>();
        HashMap<String, PropType> names = new HashMap<String, PropType>();

        Type superType = null;
        // Check if inheritance
        if (ctx.INHERITS() != null) {
            if (ctx.VAR(1) instanceof ErrorNodeImpl) {
                throw new ParserException("Invalid supertype name");
            }
            Var superTypeName = new Var(ctx.VAR(1).getText());
            // Check if the supertype exists
            superType = visitType(superTypeName);
            if (superType == null) {
                throw new ParserException("Type " + typeName.getText() + " is not defined");
            }
            for (PropType p: superType.getProperties()) {
                properties.add(p);
                names.put(p.getName().getText(), p);
            }
        }

        for (AutomateSimParser.PropertyContext p : ctx.property()) {
            PropType property = (PropType) p.accept(this);
            // Check for duplicate property names
            PropType duplicate = names.get(property.getName().getText());
            if (duplicate instanceof EnumType && property instanceof EnumType) {
                List<Var> newStates = new ArrayList<>(((EnumType) duplicate).getStates());
                for (Var state : ((EnumType) property).getStates()) {
                    if (((EnumType) duplicate).getStates().contains(state)) {
                        throw new ParserException("Duplicate enum value " + state.getText());
                    } else {
                        newStates.add(state);
                    }
                }
                property = new EnumType(property.getName(), newStates);
                names.put(property.getName().getText(), property);
                properties.remove(duplicate);
                properties.add(property);
            } else if (duplicate != null) {
                throw new ParserException("Duplicate property name: " + property.getName());
            } else {
                names.put(property.getName().getText(), property);
                properties.add(property);
            }
        }

        Type createdType = new Type(typeName, superType, properties);
        addedType.add(createdType);

        return createdType;
    }


    @Override
    public StringType visitString(AutomateSimParser.StringContext ctx) {
        if (ctx.VAR() instanceof ErrorNodeImpl) {
            throw new ParserException("Invalid string variable name");
        }
        StringType st = new StringType(new Var(ctx.VAR().getText()));
        return st;
    }
}


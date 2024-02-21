package evaluator;

import ast.*;

public class AutomateSimChecker extends AutomateSimBaseVisitor<StringBuilder, String> {
    /*
     * TODO: starter for the static checker. some notes added with each method for what things to check,
     *  but the list is not necessarily complete. feel free to change or add anything.
     *  add fields here as needed for checking, e.g. map of devices, props, types. don't use `Context` or `TestContext`.
     *  delete this comment block when u done.
     */

    @Override
    public String visit(StringBuilder context, Action p) {
        /*
         * TODO: has an action with the same name been declared already?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, Room p) {
        /*
         * TODO: has a room with the same name been declared already?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, Device p) {
        /*
         * TODO: has a device with the same name been declared already?
         *       is the device type valid? (i.e. has it been declared?)
         *       has all of its props been initialized, including supertype props? (not sure about the ordering of prop
         *          values, i think supertype props should come first but check with parser ppl)
         *       are the initialized values valid?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, DeviceProp p) {
        /*
         * TODO: does the device exist?
         *       does the prop exist on that device?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, Type p) {
        /*
         * TODO: has a type with the same name been declared already?
         *       is the supertype valid?
         *       has the type tried to improperly redeclare any supertype props?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, NumberVal p) {
        /*
         * TODO: is the value valid? (i.e. is it within the number type's min and max range?)
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, EnumVal p) {
        /*
         * TODO: is the value valid? (i.e. is it one of the defined states for the enum type?)
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, SetStatement p) {
        /*
         * for reference: set <dst> to <value or src>
         * TODO: if static: is the value valid for the dst prop's type?
         *       if dynamic: do the device types match? (i.e. are the types equal, or is the src type a supertype of
         *          the dst type?) are the specified prop names equal?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, IfStatement p) {
        /*
         * TODO: is the if-value valid for the specified prop?
         */
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, ForStatement p) {
        /*
         * TODO: does the specified room and type exist?
         */
        return super.visit(context, p);
    }

    /*
     * TODO: probably no checks needed for below, but not certain. delete the declarations if they for sure don't
     *  need checks
     */

    @Override
    public String visit(StringBuilder context, StringVal p) {
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, NumberType p) {
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, StringType p) {
        return super.visit(context, p);
    }

    @Override
    public String visit(StringBuilder context, EnumType p) {
        return super.visit(context, p);
    }
}

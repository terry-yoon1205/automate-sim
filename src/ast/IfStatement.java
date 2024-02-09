package ast;

import java.util.List;

public class IfStatement extends Statement {
    private final DeviceProp deviceProp;
    private final PropVal value;
    private final List<Statement> statements;

    public IfStatement(DeviceProp deviceProp, PropVal value, List<Statement> statements) {
        this.deviceProp = deviceProp;
        this.value = value;
        this.statements = statements;
    }

    public DeviceProp getDeviceProp() {
        return deviceProp;
    }

    public PropVal getValue() {
        return value;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

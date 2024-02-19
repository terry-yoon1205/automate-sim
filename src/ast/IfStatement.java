package ast;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IfStatement that)) return false;
        return Objects.equals(deviceProp, that.deviceProp) && Objects.equals(value, that.value) && Objects.equals(statements, that.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceProp, value, statements);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

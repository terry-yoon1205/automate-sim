package ast;

import java.util.Objects;

public class SetStatement extends Statement {
    private final DeviceProp deviceProp;

    // only one of the two below will be non-null depending on the argument of the set statement
    private final PropVal staticVal;
    private final DeviceProp dynamicVal;

    public SetStatement(DeviceProp deviceProp, PropVal staticVal) {
        this.deviceProp = deviceProp;
        this.staticVal = staticVal;
        this.dynamicVal = null;
    }

    public SetStatement(DeviceProp deviceProp, DeviceProp dynamicVal) {
        this.deviceProp = deviceProp;
        this.staticVal = null;
        this.dynamicVal = dynamicVal;
    }

    public DeviceProp getDeviceProp() {
        return deviceProp;
    }

    public PropVal getStaticVal() {
        return staticVal;
    }

    public DeviceProp getDynamicVal() {
        return dynamicVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetStatement that)) return false;
        return Objects.equals(deviceProp, that.deviceProp) && Objects.equals(staticVal, that.staticVal) && Objects.equals(dynamicVal, that.dynamicVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceProp, staticVal, dynamicVal);
    }

    @Override
    public <C, T> T accept(C context, Visitor<C, T> v) {
        return v.visit(context, this);
    }
}

package model;

public class StringProp extends Property {
    public StringProp(String name, String value) {
        super(name, value);
    }

    @Override
    protected boolean isValid(String value) {
        return true;
    }
}

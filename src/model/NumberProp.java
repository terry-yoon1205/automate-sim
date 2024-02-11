package model;

public class NumberProp extends Property {
    private int min;
    private int max;

    public NumberProp(String name, String value) {
        super(name, value);
    }

    @Override
    protected boolean isValid(String value) {
        try {
            int val = Integer.parseInt(value);
            return val >= min && val <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

package model;

public class NumberProp extends Property {
    private final int min;
    private final int max;

    public NumberProp(String name, String value, int min, int max) {
        super(name, value);
        this.min = min;
        this.max = max;
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

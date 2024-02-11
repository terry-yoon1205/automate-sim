package model;

import java.util.Set;

public class EnumProp extends Property {
    private final Set<String> states;

    public EnumProp(String name, String initVal, Set<String> states) {
        super(name, initVal);
        this.states = states;
    }

    @Override
    protected boolean isValid(String state) {
        return states.contains(state);
    }
}

package model;

import model.context.TestContext;
import model.interfaces.Subject;

public abstract class Property extends Subject {
    private final String name;
    private String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public boolean mutate(String value) {
        if (!isValid(value)) {
            return false;
        }

        this.value = value;
        notifyObservers();
        return true;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    protected abstract boolean isValid(String value);
}

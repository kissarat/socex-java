package store.socex.core.here;

import store.socex.core.simple.DirectJoint;

public class KeyJoint<T> extends DirectJoint {
    protected String name;
    protected T value;

    public KeyJoint(String name, T value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getJointId() {
        return name;
    }
}

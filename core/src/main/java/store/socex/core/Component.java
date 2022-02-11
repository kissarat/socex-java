package store.socex.core;

import store.socex.core.simple.SimpleJoint;

public interface Component extends SimpleJoint {
    default String getId() { return getJointId(); };
    default String getComponentName() { return getClass().getName(); };
}

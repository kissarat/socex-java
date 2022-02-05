package store.socex.lib;

import store.socex.lib.simple.SimpleJoint;

public interface Component extends SimpleJoint {
    default String getId() { return getJointId(); };
    default String getComponentName() { return getClass().getName(); };
}

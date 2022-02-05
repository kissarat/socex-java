package store.socex.lib.simple;

import store.socex.lib.Joint;

public interface SimpleJoint extends Joint<String, SimpleDomain> {
    default String getJointId() { return getClass().getName(); };
}

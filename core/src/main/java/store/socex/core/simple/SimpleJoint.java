package store.socex.core.simple;

import store.socex.core.Joint;

public interface SimpleJoint extends Joint<String, SimpleDomain> {
    default String getJointId() { return getClass().getName(); };
}

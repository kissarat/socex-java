package store.socex.lib.simple;

import store.socex.lib.Domain;

public interface SimpleDomain extends Domain<String, SimpleJoint, SimpleDomain> {
    String getDomainName();
    String getPathSplitter();

    default String createJointName(SimpleJoint joint) {
        return getDomainName() + getPathSplitter() + joint.getJointId();
    }
}

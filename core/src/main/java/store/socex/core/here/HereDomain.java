package store.socex.core.here;

import store.socex.core.simple.SimpleDomain;
import store.socex.core.simple.SimpleJoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

public class HereDomain implements SimpleDomain {
    private String name;
    private Collection<SimpleJoint> joints;

    public HereDomain(String name) {
        this.name = name;
        this.joints = new ArrayList<>();
    }

    @Override
    public void bind(SimpleJoint joint) {
        joints.add(joint);
    }

    @Override
    public SimpleJoint get(String id) {
        for(var joint: this.joints) {
            if (joint.getJointId().equals(name)) {
                return joint;
            }
        }
        return null;
    }

    @Override
    public void unbind(String id) {
        this.joints.removeIf(joint -> joint.getJointId().equals(id));
    }

    @Override
    public <XTo extends SimpleDomain> XTo createSubdomain() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public String getDomainName() {
        return this.name;
    }

    @Override
    public String getPathSplitter() {
        return ":";
    }
}

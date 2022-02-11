package store.socex.core.simple;

public class DirectJoint implements SimpleJoint {
    @Override
    public String attachedBy(SimpleDomain domain) {
        var joint = domain.get(this.getJointId());
        if (null == joint) {
            return this.getJointId();
        }
        return joint.getJointId();
    }

    @Override
    public String attach(SimpleDomain domain) {
        domain.bind(this);
        return this.getJointId();
    }

    @Override
    public void detach(SimpleDomain domain) {

    }
}

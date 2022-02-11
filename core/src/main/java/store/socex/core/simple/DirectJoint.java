package store.socex.core.simple;

public class DirectJoint implements SimpleJoint {
    public String nameForDomain(SimpleDomain domain) {
        return getJointId() + ':' + domain.getClass().getName();
    }

    @Override
    public String attachedBy(SimpleDomain domain) {
        return nameForDomain(domain);
    }

    @Override
    public String attach(SimpleDomain domain) {
        return nameForDomain(domain);
    }

    @Override
    public void detach(SimpleDomain domain) {

    }
}

package store.socex.lib.here;

import store.socex.lib.simple.SimpleDomain;
import store.socex.lib.simple.SimpleJoint;

public class HereDomain implements SimpleDomain {

    @Override
    public void bind(SimpleJoint joint) {

    }

    @Override
    public Object getObject(String name) {
        return null;
    }

    @Override
    public Object get(String s) {
        return null;
    }

    @Override
    public void unbind(String s) {

    }

    @Override
    public <XTo extends SimpleDomain> XTo createSubdomain() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}

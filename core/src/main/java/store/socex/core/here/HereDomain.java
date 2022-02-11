package store.socex.core.here;

import store.socex.core.simple.SimpleDomain;
import store.socex.core.simple.SimpleJoint;

import java.util.Hashtable;
import java.util.Map;

public class HereDomain implements SimpleDomain {
    private String name;
    private Map<String, Object> components;

    public HereDomain(String name) {
        this.name = name;
        this.components = new Hashtable<>();
    }

    @Override
    public void bind(SimpleJoint joint) {}

    @Override
    public Object getObject(String name) {
        return "";
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

    @Override
    public String getDomainName() {
        return this.name;
    }

    @Override
    public String getPathSplitter() {
        return ":";
    }
}

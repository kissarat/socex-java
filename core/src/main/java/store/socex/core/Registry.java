package store.socex.core;

import store.socex.core.here.HereDomain;
import store.socex.core.simple.SimpleDomain;

import java.util.ArrayList;
import java.util.List;

public class Registry extends HereDomain implements SimpleDomain {
    protected final List<Component> instances;

    public Registry(String name, List<Component> instances) {
        super(name);
        this.instances = instances;
    }

    public Registry() {
        this("registry", new ArrayList<>());
    }
}

package store.socex.lib;

import store.socex.lib.here.HereDomain;
import store.socex.lib.simple.SimpleDomain;

import java.util.ArrayList;
import java.util.List;

public class Registry extends HereDomain implements SimpleDomain {
    protected final List<Component> singletones;

    public Registry(String name, List<Component> singletones) {
        super(name);
        this.singletones = singletones;
    }

    public Registry() {
        this("registry", new ArrayList<>());
    }
}

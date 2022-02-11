package store.socex;

import java.util.Map;

public class Subdomain extends Domain {
    protected Locator parent;

    public Subdomain(Locator parent, Map<String, Object> instances) {
        super(instances);
        this.parent = parent;
    }

    @Override
    public Object resolve(String key) {
        if (instances.containsKey(key)) {
            return instances.get(key);
        }
        return super.resolve(key);
    }
}

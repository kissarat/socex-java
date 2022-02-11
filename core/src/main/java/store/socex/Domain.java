package store.socex;

import store.socex.settings.Settings;

import java.util.Hashtable;
import java.util.Map;

public class Domain implements Locator {
    protected final Map<String, Object> instances;
    public void bind(String key, Object value) {
        this.instances.put(key, value);
    }

    public static Map<String, Object> createMap() {
        return new Hashtable<>();
    }

    public Domain(Map<String, Object> instances) {
        this.instances = instances;
    }

    @Override
    public Object createInstance(String key) {
        return null;
    }

    public Object resolve(String key) {
        Object instance;
        if (instances.containsKey(key)) {
            instance = instances.get(key);
        } else  {
            instance = createInstance(key);
            if (null != instance) {
                instances.put(key, instance);
            }
        }
        return instance;
    }
}

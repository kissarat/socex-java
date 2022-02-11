package store.socex.settings;

import java.util.ArrayList;
import java.util.Collection;

public class ChildSettings extends AbstractSettings {
    private final Settings parent;
    private final String name;

    public ChildSettings(Settings parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String getId() {
        if (null == this.parent) {
            return name;
        }
        return parent.getId() + '.' + name;
    }

    protected String createKey(String key) {
        return (name + '.') + key;
    }

    @Override
    public String getProperty(String key) {
        return parent.getProperty(createKey(key));
    }
    @Override
    public void setProperty(String key, String value) {
        parent.setProperty(createKey(key), value);
    }

    @Override
    public Iterable<String> keys() {
        Collection<String> keys = new ArrayList<>();
        String prefix = getId() + '.';
        for(var key: parent.keys()) {
            if (key.startsWith(prefix)) {
                keys.add(key);
            }
        }
        return keys;
    }

    @Override
    public Settings derive(String name) {
        return new ChildSettings(this, createKey(name));
    }
}

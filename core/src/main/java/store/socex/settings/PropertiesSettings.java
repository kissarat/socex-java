package store.socex.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class PropertiesSettings extends SourceSettings {
    private final Properties properties;

    public PropertiesSettings(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    @Override
    public void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    @Override
    public Settings derive(String name) {
        return new ChildSettings(this, name);
    }

    @Override
    public Iterable<String> keys() {
        return PropertiesSettings.keysOf(properties);
    }

    public static Iterable<String> keysOf(Properties properties) {
        Collection<String> keys = new ArrayList<>();
        for(var key: properties.keySet()) {
            keys.add(key.toString());
        }
        return keys;
    }
}

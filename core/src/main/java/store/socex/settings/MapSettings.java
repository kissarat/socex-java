package store.socex.settings;

import java.util.Map;

public class MapSettings extends SourceSettings {
    protected final Map<String, String> map;

    public MapSettings(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String getProperty(String name) {
        return map.get(name);
    }

    @Override
    public void setProperty(String name, String value) {
        map.put(name, value);
    }

    @Override
    public Iterable<String> keys() {
        return map.keySet();
    }
}

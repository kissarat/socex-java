package store.socex.settings;

public final class SystemSettings extends SourceSettings {
    @Override
    public String getProperty(String key) {
        return System.getProperty(key);
    }

    @Override
    public void setProperty(String key, String value) {
        System.setProperty(key, value);
    }

    private static final SystemSettings instance = new SystemSettings();

    public static Settings get(String name) {
        return instance.derive(name);
    }

    public static Settings getInstance() {
        return instance;
    }

    @Override
    public Iterable<String> keys() {
        return PropertiesSettings.keysOf(System.getProperties());
    }
}

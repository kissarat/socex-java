package store.socex.settings;

public class SettingsProxy implements Settings {
    protected final Settings settings;
    public SettingsProxy(Settings settings) {
        this.settings = settings;
    }

    @Override
    public Settings derive(String name) {
        return new SettingsProxy(this.settings.derive(name));
    }

    @Override
    public String getProperty(String name) {
        return this.settings.getProperty(name);
    }

    @Override
    public void setProperty(String name, String value) {
        this.settings.setProperty(name, value);
    }

    @Override
    public int getInteger(String name) {
        return this.settings.getInteger(name);
    }

    @Override
    public void setInteger(String name, int value) {
        this.settings.setInteger(name, value);
    }

    @Override
    public boolean getBoolean(String name) {
        return this.settings.getBoolean(name);
    }

    @Override
    public void setBoolean(String name, boolean value) {
        this.settings.setBoolean(name, value);
    }

    @Override
    public float getFloat(String name) {
        return this.settings.getFloat(name);
    }

    @Override
    public void setFloat(String name, float value) {
        this.settings.setFloat(name, value);
    }

    @Override
    public double getDouble(String name) {
        return this.settings.getDouble(name);
    }

    @Override
    public void setDouble(String name, double value) {
        this.settings.setDouble(name, value);
    }

    @Override
    public Class<?> getType(String name) {
        return this.settings.getType(name);
    }

    @Override
    public Iterable<String> keys() {
        return this.settings.keys();
    }
}

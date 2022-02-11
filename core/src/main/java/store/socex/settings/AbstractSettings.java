package store.socex.settings;

public abstract class AbstractSettings implements Settings {
    @Override
    public int getInteger(String key) {
        return Integer.parseInt(getProperty(key));
    }

    @Override
    public void setInteger(String name, int value) {
        setProperty(name, Integer.toString(value));
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    @Override
    public void setBoolean(String name, boolean value) {
        setProperty(name, Boolean.toString(value));
    }

    @Override
    public float getFloat(String key) {
        return Float.parseFloat(getProperty(key));
    }

    @Override
    public void setFloat(String name, float value) {
        setProperty(name, Float.toString(value));
    }

    @Override
    public double getDouble(String key) {
        return Double.parseDouble(getProperty(key));
    }

    @Override
    public void setDouble(String name, double value) {
        setProperty(name, Double.toString(value));
    }   
}

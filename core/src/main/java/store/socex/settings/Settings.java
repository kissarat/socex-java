package store.socex.settings;

import store.socex.Joint;

public interface Settings extends Joint {
    Settings derive(String name);
    String getProperty(String name);
    void setProperty(String name, String value);
    int getInteger(String name);
    void setInteger(String name, int value);
    boolean getBoolean(String name);
    void setBoolean(String name, boolean value);
    float getFloat(String name);
    void setFloat(String name, float value);
    double getDouble(String name);
    void setDouble(String name, double value);
    default Class<?> getType(String name) {
        return String.class;
    }
    Iterable<String> keys();
}

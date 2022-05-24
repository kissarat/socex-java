package socex.core.scheme;

import java.util.Properties;

public class PropertiesScheme implements Scheme {
    private final Scheme scheme;
    private final Properties properties;

    public PropertiesScheme(Scheme scheme, Properties properties) {
        this.scheme = scheme;
        this.properties = properties;
    }

    @Override
    public String getString(String key) {
        return properties.getProperty(key, scheme.getString(key));
    }

    @Override
    public void delString(String key) {
        scheme.delString(key);
    }

    @Override
    public boolean exists(String key) {
        return properties.contains(key) || scheme.exists(key);
    }

    @Override
    public void setString(String key, String value) {
        scheme.setString(key, value);
    }
}

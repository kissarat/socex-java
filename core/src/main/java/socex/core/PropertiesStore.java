package socex.core;

import java.io.IOException;


public interface PropertiesStore {
    void load() throws Exception;
    void store() throws Exception;
    String getProperty(String key);
    Object setProperty(String key, String value);
}

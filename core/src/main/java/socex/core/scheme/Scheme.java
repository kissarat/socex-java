package socex.core.scheme;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public interface Scheme {
    String getString(String key);
    void delString(String key);
    boolean exists(String key);
    void setString(String key, String value);
    default void setBoolean(String key, boolean value) {
        if (value) {
            setString(key, "1");
        } else {
            delString(key);
        }
    }
    default boolean getBoolean(String key) {
        return exists(key);
    }

}

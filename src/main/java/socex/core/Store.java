package socex.core;

public interface Store {
    String get(String key);
    String require(String key) throws PropertyError;

    void set(String key, String value, int seconds);
    default void set(String key, String value) {
        set(key, value, 0);
    }
    void del(String key);
    Store derive(String namespace);
    default String getNamespace() {
        return  "";
    }

    default boolean isEmpty(String key) {
        String value = get(key);
        return value == null || value.length() == 0;
    }

    default boolean getBoolean(String key) {
        String value = get(key);
        return !(value != null && value.length() > 0 && !value.equals("0"));
    }

    default void setBoolean(String key, boolean value) {
        if (value) {
            set(key, "1");
        } else {
            del(key);
        }
    }
}

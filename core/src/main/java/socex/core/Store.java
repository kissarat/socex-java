package socex.core;

public interface Store {
    String get(String key);
    void set(String key, String value, int seconds);
    default void set(String key, String value) {
        set(key, value, 0);
    }
    void del(String key);
    Store derive(String namespace);
    default String getNamespace() {
        return  "";
    }
}

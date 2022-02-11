package store.socex;

public interface Locator {
    Object createInstance(String key);
    Object resolve(String key);
}

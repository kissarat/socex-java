package store.socex;

public interface Component extends Joint {
    default String getComponentName() { return getClass().getName(); }
    default String getId() { return getComponentName(); }
}

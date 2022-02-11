package store.socex;

public interface Joint {
    default String getId() { return getClass().getName(); }
}

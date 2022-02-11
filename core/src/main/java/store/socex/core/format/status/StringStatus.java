package store.socex.core.format.status;

public interface StringStatus {
    String toString();
    default String get() {
        return toString();
    }
}

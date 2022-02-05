package store.socex.lib.format.status;

public interface StringStatus {
    String toString();
    default String get() {
        return toString();
    }
}

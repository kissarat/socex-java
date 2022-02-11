package store.socex.core;

public interface Service {
    default String getServiceName() {
        return getClass().getName();
    }
}

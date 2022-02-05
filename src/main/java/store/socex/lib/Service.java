package store.socex.lib;

public interface Service {
    default String getServiceName() {
        return getClass().getName();
    }
}

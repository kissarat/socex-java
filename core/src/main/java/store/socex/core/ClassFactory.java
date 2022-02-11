package store.socex.core;

public interface ClassFactory {
    default ClassLoader getClassLoader() {
        return ClassLoader.getSystemClassLoader();
    };
    default Class<?> getType(String name) throws ClassNotFoundException {
        return getClassLoader().loadClass(name);
    }
    <T> T getBean(Class<T> requiredType);
}

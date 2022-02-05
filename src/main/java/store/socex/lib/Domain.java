package store.socex.lib;

public interface Domain<By, Join extends Joint<By, To>, To extends Domain<?, ?, ?>> extends AutoCloseable, Cloneable {
    void bind(Join joint);
    Object getObject(By name);
    Object get(By by);
    void unbind(By by);
    <XTo extends To> XTo createSubdomain();
}

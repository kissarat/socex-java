package store.socex.core;

public interface Domain<By, Join extends Joint<By, To>, To extends Domain<?, ?, ?>> extends AutoCloseable, Cloneable {
    void bind(Join joint);
    Join get(By by);
    void unbind(By by);
    <XTo extends To> XTo createSubdomain();
}

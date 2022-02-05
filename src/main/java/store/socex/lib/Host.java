package store.socex.lib;

import store.socex.lib.lambda.Apply;
import store.socex.lib.lambda.Assign;

public interface Host<Belong extends Assign<String, Belong>> extends Assign<String, Belong >, AutoCloseable, Cloneable {
    default void bind(Belong belong) { belong.is().of(this)};
    void bind(Belong belong);
    Object getObject(String name);
    void unbind(String name);
    void unbind(String name);
    <V> Host<V> createContext();
    <V extends Apply> Host<V> createChildContext();
}

package store.socex.lib;

public interface ChildContext<Value> extends Host<Value> {
    Host<Value> getParent();
}

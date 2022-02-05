package store.socex.lib;

import java.util.Map;

public abstract class MapContext<Value> implements Host<Value> {
    protected Map<String, Object> map;

    @Override
    public void bind(String name, Object object) {
        map.put(name, object);
    }

    @Override
    public Object getObject(String name) {
        return map.get(name);
    }

    @Override
    public void unbind(String name) {
        map.remove(name);
    }

    @Override
    public void close() throws Exception {
        map.clear();
    }
}

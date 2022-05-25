package socex.core;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class JedisStore implements Store {
    private final Jedis jedis;
    private final String namespace;

    public JedisStore(Jedis jedis, String namespace) {
        this.jedis = jedis;
        this.namespace = namespace;
    }

    public JedisStore(String namespace) {
        this(new Jedis(), namespace);
    }

    public JedisStore() {
        this("");
    }

    @Override
    public String get(String key) {
        return jedis.get(createKey(key));
    }

    @Override
    public String require(String key) throws PropertyError {
        String name = createKey(key);
        String value = jedis.get(name);
        if (value == null || value.length() == 0) {
            throw new PropertyError(name);
        }
        return value;
    }

    @Override
    public void set(String key, String value, int seconds) {
        jedis.set(createKey(key), value, SetParams.setParams().ex(seconds));
    }

    @Override
    public void set(String key, String value) {
        jedis.set(createKey(key), value);
    }

    @Override
    public void del(String key) {
        jedis.del(createKey(key));
    }

    private String createKey(String key) {
        return namespace.length() > 0 ? String.format("%s.%s", namespace, key) : key;
    }

    @Override
    public Store derive(String name) {
        return new JedisStore(jedis, createKey(name));
    }

    @Override
    public String getNamespace() {
        return namespace;
    }
}

package socex.core.scheme;


import redis.clients.jedis.Jedis;

public class RedisScheme implements Scheme {
    private final Jedis jedis;

    public RedisScheme(Jedis jedis) {
        this.jedis = jedis;
    }

    public RedisScheme() {
        this(new Jedis(System.getProperty("scheme.redis.url", "redis://localhost/0")));
    }

    @Override
    public String getString(String key) {
        return jedis.get(key);
    }

    @Override
    public void delString(String key) {
        jedis.del(key);
    }

    @Override
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public void setString(String key, String value) {
        jedis.set(key, value);
    }
}

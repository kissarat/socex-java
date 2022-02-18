package store.socex.media;

public interface Poster {
    void post(Post post) throws Exception;
    String getUserAgent();
    void setUserAgent(String value);
}

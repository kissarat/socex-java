package socex.core.media;

public interface Poster {
    String publish(Post post) throws Exception;
    boolean isEnabled();
}

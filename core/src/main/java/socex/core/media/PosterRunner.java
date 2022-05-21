package socex.core.media;

import socex.core.media.telegram.TelegramBot;

import java.util.*;

public class PosterRunner implements Poster {
    private final Collection<Poster> posters;

    public PosterRunner(Collection<Poster> posters) {
        this.posters = posters;
    }

    @Override
    public void publish(Post post) throws Exception {
        for(var poster: posters) {
                poster.publish(post);
        }
    }

    public static void main(String[] args) {
        final PosterRunner runner = new PosterRunner(Arrays.asList(new TelegramBot()));
        try {
            runner.publish(new Post(args[0]));
        } catch (Exception e) {
//            e.printStackTrace();
            System.err.println(e.toString());
        }
    }
}
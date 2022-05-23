package socex.core.media;

import socex.core.media.telegram.TelegramBot;

import java.io.FileInputStream;
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
        try {
            var stream = new FileInputStream(args[0]);
            var properties = new Properties();
            properties.load(stream);
            PosterRunner runner = new PosterRunner(Arrays.asList(new TelegramBot(
                    properties.getProperty("telegram.bot.token"),
                    properties.getProperty("telegram.bot.chat")
            )));
            runner.publish(new Post(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

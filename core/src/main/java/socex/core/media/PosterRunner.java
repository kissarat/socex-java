package socex.core.media;

import socex.core.media.telegram.TelegramBot;

import java.util.ArrayList;
import java.util.Collection;

public class PosterRunner implements Runnable {
    private final Collection<Poster> posters;

    public PosterRunner() {
        posters = new ArrayList<>();
        if (null != System.getProperty("telegram.bot.token")) {
            posters.add(new TelegramBot());
        }
    }

    @Override
    public void run() {
        for(var poster: posters) {
            try {
                poster.post(new Post(System.getProperty("text")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

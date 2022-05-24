package socex.core.media;

import socex.core.media.facebook.FacebookPoster;
import socex.core.media.telegram.TelegramPoster;

import java.io.*;
import java.util.*;

public class PosterRunner {
    private final Collection<Poster> posters;
    private Properties properties;

    public PosterRunner(Collection<Poster> posters) {
        this.posters = posters;
    }

    public void addPoster(Poster poster) {
        posters.add(poster);
    }

    public void publish(Post post) throws Exception {
        for(var poster: posters) {
                System.out.println(poster.publish(post));
        }
    }

    private void readProperties() throws IOException {
        var stream = new FileInputStream("/Users/ukraine/own/socex-java/core/src/main/resources/application.properties");
        properties = new Properties();
        properties.load(stream);
    }

    private FacebookPoster createFacebookPoster() {
        return new FacebookPoster(
                properties.getProperty("facebook.app.id"),
                properties.getProperty("facebook.app.secret"),
                properties.getProperty("facebook.page")
        );
    }

    public static void main(String[] args) {
        try {
            var hub = new PosterRunner(new ArrayList<>());
            hub.readProperties();
            for(String command: args) {
                if (command.equals("telegram")) {
                    hub.addPoster(new TelegramPoster(
                            hub.properties.getProperty("telegram.bot.token"),
                            hub.properties.getProperty("telegram.bot.chat")
                    ));
                } else if (command.equals("facebook")) {
                    hub.addPoster(hub.createFacebookPoster());
                } else if (command.equals("facebook-token")) {
                    var facebook = hub.createFacebookPoster();
                    System.out.println(facebook.getGrantAccessTokenURL());
                    var reader = new BufferedReader(new InputStreamReader(System.in));
                    facebook.setAccessToken(reader.readLine());
                } else {
                    hub.publish(new Post(command));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

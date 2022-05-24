package socex.core.media;

import socex.core.JedisStore;
import socex.core.Store;
import socex.core.StorePoster;
import socex.core.media.facebook.FacebookAuth;
import socex.core.media.facebook.FacebookPoster;
import socex.core.media.telegram.TelegramPoster;

import java.io.*;
import java.util.*;

public class PosterRunner extends StorePoster {
    private final Map<String, Poster> posters;

    public PosterRunner(Store store) {
        super(store);
        posters = new HashMap<>();
        posters.put("telegram", new TelegramPoster(store.derive("telegram")));
        posters.put("facebook", new FacebookPoster(store.derive("facebook")));
    }

    public Map<String, String> publish(Post post) throws Exception {
        var ids = new HashMap<String, String>();
        for(var entry: posters.entrySet()) {
            Poster poster = entry.getValue();
            if (poster.isEnabled()) {
                String id = poster.publish(post);
                ids.put(entry.getKey(), id);
            }
        }
        return ids;
    }

    public static void main(String[] args) {
        try {
            var hub = new PosterRunner(new JedisStore());
            for(String command: args) {
                if (command.equals("facebook-token")) {
                    var facebook = (FacebookAuth) hub.posters.get("facebook");
                    System.out.println(facebook.getGrantAccessTokenURL());
                    var reader = new BufferedReader(new InputStreamReader(System.in));
                    facebook.setAccessToken(reader.readLine());
                } else {
                    var ids = hub.publish(new Post(command));
                    for(String id: ids.keySet()) {
                        System.out.printf("%s=%s%n", id, ids.get(id));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

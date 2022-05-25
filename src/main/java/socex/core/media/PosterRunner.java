package socex.core.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socex.core.JedisStore;
import socex.core.Store;
import socex.core.StorePoster;
import socex.core.media.facebook.FacebookPoster;
import socex.core.media.telegram.TelegramPoster;

import java.util.*;

public class PosterRunner extends StorePoster {
    final static Logger logger = LoggerFactory.getLogger(PosterRunner.class);
    private final Map<String, Poster> posters;

    public PosterRunner(Store store) {
        super(store);
        posters = new HashMap<>();
    }

    public Poster createPoster(String type, String name) throws Exception {
        if (type.equals(TelegramPoster.POSTER_TYPE)) {
            return new TelegramPoster(store.derive(name));
        } else if (type.equals(FacebookPoster.POSTER_TYPE)) {
            return new FacebookPoster(store.derive(name));
        } else {
            throw new Exception("Unknown poster type " + type);
        }
    }

    public void addPoster(String name, Poster poster) {
        posters.put(name, poster);
    }

    public void addPoster(String name, String type) throws Exception {
        posters.put(name, createPoster(type, name));
    }

    public void addPoster(String name) throws Exception {
        addPoster(name, name);
    }

    public void checkPosters() {
        for(var entry: posters.entrySet()) {
            if (!entry.getValue().isEnabled()) {
                logger.warn(entry.getKey() + " posting is disabled");
            }
        }
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

    private FacebookPoster getFacebookPoster() {
        return (FacebookPoster) this.posters.get("facebook");
    }

    public static void main(String[] args) {
        try {
            var hub = new PosterRunner(new JedisStore());
            hub.addPoster(TelegramPoster.POSTER_TYPE);
            hub.addPoster(FacebookPoster.POSTER_TYPE);
            String command = args[0];
            if (command.equals("facebook-login")) {
                System.out.println(hub.getFacebookPoster().createLoginDialogURL(args[1]));
            }
            if (command.equals("facebook-client-code")) {
                System.out.println(hub.getFacebookPoster().createClientCodeURL(args[1], args[2]));
            }
            if (command.equals("facebook-token")) {
                hub.getFacebookPoster().setAccessToken(args[1], Integer.parseInt(args[2]));
            }
            if (command.equals("facebook-post")) {
                hub.getFacebookPoster().publish(new Post(args[1]));
            }
            if (command.equals("facebook-permissions")) {
                System.out.println(hub.getFacebookPoster().requestPermissions().toString(2));
            }
            if (command.equals("facebook-app-token")) {
                System.out.println(hub.getFacebookPoster().createAppAccessTokenURL());
            }
            if (command.equals("facebook-page-token")) {
                System.out.println(hub.getFacebookPoster().createUserAccessTokenURL(args[1], args[2]));
            }
//            var facebookPrefix = "facebook-";
//            for(String command: args) {
//                if (command.equals("facebook-c"))
//                    System.out.println(facebook.createAuthURL(command.substring(facebookPrefix.length())));
////                    var reader = new BufferedReader(new InputStreamReader(System.in));
////                    facebook.setAccessToken(reader.readLine());
//                    return;
//                } else {
//                    var ids = hub.publish(new Post(command));
//                    for(String id: ids.keySet()) {
//                        System.out.printf("%s=%s%n", id, ids.get(id));
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

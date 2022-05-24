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
        posters.put("telegram", new TelegramPoster(store.derive("telegram")));
        posters.put("facebook", new FacebookPoster(store.derive("facebook")));
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

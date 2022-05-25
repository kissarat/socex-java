package socex.core.media.telegram;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socex.core.Store;
import socex.core.http.*;
import socex.core.media.HttpPoster;
import socex.core.media.Post;
import socex.core.media.Poster;

import java.io.IOException;

public class TelegramPoster extends HttpPoster implements Poster {
    public static final String POSTER_TYPE = "telegram";

    final static Logger logger = LoggerFactory.getLogger(TelegramPoster.class);

    @Override
    public String getPath() {
        return String.format("https://api.telegram.org/bot%s/sendMessage", store.get("bot.token"));
    }

    @Override
    public String getMethod() {
        return "GET";
    }

    @Override
    public StringDictionary getQuery() {
        return super.getQuery()
                .set("chat_id", store.get("bot.chat"));
    }

    public TelegramPoster(Store store) {
        super(store);
    }

    protected String sendMessage(String text) throws IOException, HttpResponseError {
        var options = inheritRequestDefaults();
        options
                .getQuery()
                .put("text", text);
        var responseJSON = request(options);
        if (responseJSON.getBoolean("ok")) {
            String id = Integer.toString(responseJSON.getJSONObject("result").getInt("message_id"));
            logger.trace("Posted {}", id);
            return id;
        }
        throw new JsonResponseError(0, responseJSON);
    }

    @Override
    public String publish(Post post) throws Exception {
        return sendMessage(post.getText());
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !store.isEmpty("bot.token");
    }
}

package socex.core.media.telegram;

import org.json.JSONObject;
import socex.core.Store;
import socex.core.http.*;
import socex.core.media.HttpPoster;
import socex.core.media.Post;
import socex.core.media.Poster;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TelegramPoster extends HttpPoster implements Poster {
    @Override
    public String getPath() {
        return String.format("https://api.telegram.org/bot%s/sendMessage", store.get("bot.token"));
    }

    @Override
    public String getMethod() {
        return "GET";
    }

    @Override
    public StringDictionary getHeaders() {
        return super.getHeaders()
                .set("accept", "application/json");
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
        var options = new RequestOptions(this);
        options.getQuery().put("text", text);
        var c = httpClient.request(options);
//        System.out.println("Response: " + c.toString());
        String responseString = StringHelper.readString(c.getInputStream());
        var responseJSON = new JSONObject(responseString);
        if (HttpURLConnection.HTTP_OK == c.getResponseCode() && responseJSON.getBoolean("ok")) {
//            System.out.println(responseJSON.toString(2));
            return Integer.toString(responseJSON.getJSONObject("result").getInt("message_id"));
        }
        throw new HttpResponseError(c.getResponseCode(), responseJSON.getString("description"));
    }

    @Override
    public String publish(Post post) throws Exception {
        return sendMessage(post.getText());
    }
}

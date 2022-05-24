package socex.core.media.telegram;

import org.json.JSONObject;
import socex.core.http.RestClient;
import socex.core.http.FormEncoder;
import socex.core.http.HttpResponseError;
import socex.core.http.StringDictionary;
import socex.core.media.Post;
import socex.core.media.Poster;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TelegramPoster implements Poster {
    private final RestClient httpClient;

    public TelegramPoster(String token, String chatId) {
        var query = new StringDictionary();
        query.put("chat_id", chatId);
        var headers = new StringDictionary();
        headers.put("content-type", FormEncoder.FORM_URLENCODED);
        headers.put("accept", "application/json");
        httpClient = new RestClient(
                "https://api.telegram.org/bot" + token,
                query,
                headers
        );
    }

    public TelegramPoster() {
        this(
                System.getProperty("telegram.bot.token"),
                System.getProperty("telegram.bot.chat")
        );
    }

    public String getUserAgent() {
        return httpClient.getHeaders().get("user-agent");
    }

    public void setUserAgent(String value) {
        httpClient.getHeaders().put("user-agent", value);
    }

    protected String sendMessage(String text) throws IOException, HttpResponseError {
        var params = new StringDictionary();
        params.put("text", text);
        var c = httpClient.request("GET", "/sendMessage", params);
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

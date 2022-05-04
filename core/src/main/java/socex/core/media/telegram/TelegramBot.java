package socex.core.media.telegram;

import org.json.JSONObject;
import socex.core.http.AccessTokenHttpClient;
import socex.core.http.FormEncoder;
import socex.core.http.HttpResponseError;
import socex.core.http.StringDictionary;
import socex.core.media.Post;
import socex.core.media.Poster;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Hashtable;

public class TelegramBot implements Poster {
    private final AccessTokenHttpClient httpClient;

    public TelegramBot(String token, String chatId) {
        var query = new Hashtable<String, String>();
        query.put("chat_id", chatId);
        var headers = new Hashtable<String, String>();
        headers.put("content-type", FormEncoder.FORM_URLENCODED);
        headers.put("accept", "application/json");
        httpClient = new AccessTokenHttpClient(
                "https://api.telegram.org/bot" + token,
                new StringDictionary(query),
                new StringDictionary(headers)
        );
    }

    public TelegramBot() {
        this(
                System.getProperty("telegram.bot.token"),
                System.getProperty("telegram.bot.chat")
        );
    }

    private JSONObject invoke(String method, StringDictionary params) throws IOException, HttpResponseError {
        var c = httpClient.request("GET", "/" + method, params);
        System.out.println("Response: " + c.toString());
        String responseString = StringHelper.readString(c.getInputStream());
        var responseJSON = new JSONObject(responseString);
        if (HttpURLConnection.HTTP_OK == c.getResponseCode() && responseJSON.getBoolean("ok")) {
            return responseJSON;
        }
        throw new HttpResponseError(c.getResponseCode(), responseJSON.getString("description"));
//        throw new HttpResponseError(c.getResponseCode(), c.getResponseMessage());
    }

    @Override
    public String getUserAgent() {
        return httpClient.getHeaders().get("user-agent");
    }

    @Override
    public void setUserAgent(String value) {
        httpClient.getHeaders().put("user-agent", value);
    }

    protected void sendMessage(String text) throws IOException, HttpResponseError {
        invoke("sendMessage", StringDictionary.createSingleParameter("text", text));
    }

    @Override
    public void post(Post post) throws Exception {
        sendMessage(post.getText());
    }
}

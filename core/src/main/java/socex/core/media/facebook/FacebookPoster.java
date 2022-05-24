package socex.core.media.facebook;

import org.json.JSONObject;
import socex.core.http.RestClient;
import socex.core.http.HttpResponseError;
import socex.core.http.StringDictionary;
import socex.core.media.Post;
import socex.core.media.Poster;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.prefs.Preferences;

public class FacebookPoster implements Poster {
    private RestClient httpClient;
    private static final String PREF_TOKEN_SUFFIX = "-token";
    private final Preferences prefs = Preferences.systemNodeForPackage(FacebookPoster.class);
    private final String appId;
    private final String appSecret;
    private final String pageId;


    public FacebookPoster(String appId, String appSecret, String pageId) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.pageId = pageId;
        String token = getAccessToken();
        if (token.length() > 0) {
            var query = new StringDictionary();
            var headers = new StringDictionary();
            headers.put("accept", "application/json");
            httpClient = new RestClient(
                    String.format("https://graph.facebook.com/v13.0/%s/feed", pageId),
                    query,
                    headers
            );
        }
    }

    public String getAccessToken() {
        return prefs.get(pageId + PREF_TOKEN_SUFFIX, "");
    }

    public String getGrantAccessTokenURL() {
        return String.format("https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=%s&client_secret=%s&fb_exchange_token=SHORT-LIVED-USER-ACCESS-TOKEN", appId, appSecret);
    }

    public String getPageAccessTokenURL() {
        return String.format("https://graph.facebook.com/%s?fields=access_token&access_token=USER-ACCESS-TOKEN", pageId);
    }

    public void setAccessToken(String token) {
        prefs.put(pageId + PREF_TOKEN_SUFFIX, token);
    }

    public String getUserAgent() {
        return httpClient.getHeaders().get("user-agent");
    }

    public void setUserAgent(String value) {
        httpClient.getHeaders().put("user-agent", value);
    }

    protected String sendMessage(String text) throws IOException, HttpResponseError {
        var params = new StringDictionary();
        params.put("message", text);
        var c = httpClient.request("POST", "/feed", params);
//        System.out.println("Response: " + c.toString());
        String responseString = StringHelper.readString(c.getInputStream());
        var responseJSON = new JSONObject(responseString);
        if (HttpURLConnection.HTTP_OK == c.getResponseCode()) {
            String id = responseJSON.getString("id");
            return id;
        }
        throw new HttpResponseError(c.getResponseCode(), responseJSON.getJSONObject("error").toString(2));
    }

    @Override
    public String publish(Post post) throws Exception {
        return sendMessage(post.getText());
    }
}

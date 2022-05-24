package socex.core.media.facebook;

import org.json.JSONObject;
import socex.core.Store;
import socex.core.StorePoster;
import socex.core.http.RequestOptions;
import socex.core.http.RestClient;
import socex.core.http.HttpResponseError;
import socex.core.http.StringDictionary;
import socex.core.media.HttpPoster;
import socex.core.media.Post;
import socex.core.media.Poster;
import socex.core.Token;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public class FacebookPoster extends FacebookAuth implements Poster {
    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public StringDictionary getQuery() {
        return super.getQuery().set("access_token", store.get("token"));
    }

    @Override
    public StringDictionary getHeaders() {
        return super.getHeaders().set("accept", "application/json");
    }

    public FacebookPoster(Store store) {
        super(store);
    }

    public String getPageAccessTokenURL() {
        return String.format("https://graph.facebook.com/%s?fields=access_token&access_token=USER-ACCESS-TOKEN", store.get("page"));
    }

    protected String sendMessage(String text) throws IOException, HttpResponseError {
        var options = new RequestOptions(this);
        options
                .setPath(String.format("https://graph.facebook.com/v13.0/%s/feed", store.get("page")))
                .getQuery()
                .set("message", text);
        var c = httpClient.request(options);
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

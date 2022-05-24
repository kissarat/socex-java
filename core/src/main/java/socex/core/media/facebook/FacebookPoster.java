package socex.core.media.facebook;

import org.json.JSONObject;
import socex.core.Store;
import socex.core.http.RequestOptions;
import socex.core.http.HttpResponseError;
import socex.core.http.StringDictionary;
import socex.core.media.Post;
import socex.core.media.Poster;

import java.io.IOException;

public class FacebookPoster extends FacebookAuth implements Poster {
    public FacebookPoster(Store store) {
        super(store);
    }

    protected JSONObject requestPageFeed(RequestOptions options) throws IOException, HttpResponseError {
        options.setPath(GRAPH_ORIGIN + String.format("/%s/feed", store.get("page")));
        return authenticatedRequest(options);
    }

    protected JSONObject listPageFeed() throws IOException, HttpResponseError {
        return requestPageFeed(
                inheritRequestDefaults().setMethod("GET")
        );
    }

    protected String sendMessage(String text) throws IOException, HttpResponseError {
        var options = inheritRequestDefaults();
        options
                .getQuery()
                .set("message", text);
        var responseJSON = requestPageFeed(options);
            String id = responseJSON.getString("id");
            return id;
    }

    @Override
    public String publish(Post post) throws Exception {
        return sendMessage(post.getText());
    }
}

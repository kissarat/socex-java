package socex.core.media;

import org.json.JSONObject;
import socex.core.Store;
import socex.core.StorePoster;
import socex.core.http.*;
import socex.core.utils.StringHelper;

import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class HttpPoster extends StorePoster implements RequestDefaults {
    protected final RestClient httpClient;

    protected HttpPoster(Store store) {
        super(store);
        this.httpClient = new RestClient();
    }

    @Override
    public StringDictionary getHeaders() {
        return new StringDictionary()
                .set("user-agent", "java")
                .set("accept", "application/json");
    }
    
    protected RequestOptions inheritRequestDefaults() {
        return new RequestOptions(this);
    }

    protected JSONObject request(RequestOptions options) throws IOException, HttpResponseError {
        var c = (HttpURLConnection) httpClient.request(options);
        String responseString = StringHelper.readString(c.getInputStream());
        var responseJSON = new JSONObject(responseString);
        if (HttpURLConnection.HTTP_OK == c.getResponseCode()) {
            return responseJSON;
        }
        throw new JsonResponseError(c.getResponseCode(), responseJSON);
    }

    public boolean isEnabled() {
        return true; // !store.getBoolean("disabled");
    }
}

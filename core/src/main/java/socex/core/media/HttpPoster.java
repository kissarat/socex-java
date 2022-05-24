package socex.core.media;

import socex.core.Store;
import socex.core.StorePoster;
import socex.core.http.RequestDefaults;
import socex.core.http.RestClient;
import socex.core.http.StringDictionary;

public abstract class HttpPoster extends StorePoster implements RequestDefaults {
    protected final RestClient httpClient;

    protected HttpPoster(Store store) {
        super(store);
        this.httpClient = new RestClient();
    }

    @Override
    public StringDictionary getHeaders() {
        var headers = new StringDictionary();
        headers.put("user-agent", "java");
        return headers;
    }
}

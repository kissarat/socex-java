package socex.core.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

public class RequestOptions {
    protected String path;
    protected String method = "GET";
    protected int timeout;
    protected StringDictionary query = new StringDictionary();
    protected StringDictionary headers = new StringDictionary();

    public RequestOptions(RequestDefaults defaults) {
        this.path = defaults.getPath();
        this.method = defaults.getMethod();
        this.query.putDefaults(defaults.getQuery());
        this.headers.putDefaults(defaults.getHeaders());
        this.timeout = defaults.getTimeout();
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public int getTimeout() {
        return timeout;
    }

    public StringDictionary getQuery() {
        return query;
    }

    public StringDictionary getHeaders() {
        return headers;
    }

    public RequestOptions setPath(String path) {
        this.path = path;
        return this;
    }

    public RequestOptions setMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestOptions setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public RequestOptions setQuery(StringDictionary query) {
        this.query = query;
        return this;
    }

    public RequestOptions setHeaders(StringDictionary headers) {
        this.headers = headers;
        return this;
    }

    private static boolean hasItems(Dictionary<String, String> dictionary) {
        return null != dictionary && !dictionary.isEmpty();
    }

    public URL getURL() throws MalformedURLException {
        String url = path;
        if (hasItems(query)) {
            url += '?' + query.toString();
        }
        return new URL(url);
    }
}

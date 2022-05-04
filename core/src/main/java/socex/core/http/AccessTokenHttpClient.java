package socex.core.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class AccessTokenHttpClient {
    private final String origin;
    private final StringDictionary query;
    private final StringDictionary headers;
    private int timeout = 10 * 1000;
    private final List<String> methodsWithBody = Arrays.asList("POST", "PUT", "PATCH");

    public AccessTokenHttpClient(String origin, StringDictionary query, StringDictionary headers) {
        this.origin = origin;
        this.query = query;
        this.headers = headers;
    }
    public AccessTokenHttpClient(String origin, StringDictionary query) {
        this(origin, query, new StringDictionary());
    }

    public AccessTokenHttpClient(String origin) {
        this(origin, new StringDictionary(), new StringDictionary());
    }

    private static boolean hasItems(Dictionary<String, String> dictionary) {
        return null != dictionary && !dictionary.isEmpty();
    }

    private URL makeURL(String path, StringDictionary query) throws MalformedURLException {
        String spec = origin + path;
        if (hasItems(query)) {
            spec += '?' + query.toString();
        }
        return new URL(spec);
    }

    public HttpURLConnection request(String method, String path, StringDictionary query,
                                     StringDictionary headers) throws IOException {
        var url = makeURL(path, query.putDefaults(getQuery()));
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        if (methodsWithBody.contains(method)) {
            connection.setDoOutput(true);
            connection.setRequestProperty("content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
        }
        int timeout = getTimeout();
        if (timeout > 0) {
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
        }
        var inHeaders = hasItems(headers)
                ? headers.putDefaults(getHeaders())
                : getHeaders();
        inHeaders.forEach(connection::addRequestProperty);
        return connection;
    }

    public HttpURLConnection request(String method, String path, StringDictionary query) throws IOException {
        return request(method, path, query, null);
    }

    public StringDictionary getQuery() {
        return this.query;
    }

    public StringDictionary getHeaders() {
        return this.headers;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

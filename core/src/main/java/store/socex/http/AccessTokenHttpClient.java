package store.socex.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class AccessTokenHttpClient {
    private String origin;
    private StringDictionary query;
    private StringDictionary headers;
    private List<String> methodsWithBody = Arrays.asList("POST", "PUT", "PATCH");

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

    private static boolean isEmpty(Dictionary<String, String> dictionary) {
        return null == dictionary || dictionary.isEmpty();
    }

    private URL makeURL(String path, StringDictionary query,StringDictionary headers) throws MalformedURLException {
        String spec = origin + path;
        if (!isEmpty(query)) {
            spec += '?' + query.toString();
        }
        return new URL(spec);
    }

    public HttpURLConnection request(String method, String path, StringDictionary query,
                                     StringDictionary headers) throws IOException {
        var url = makeURL(path, query, headers);
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        if (methodsWithBody.contains(method)) {
            connection.setDoOutput(true);
        }
        if (!isEmpty(headers)) {
            headers.forEach(connection::addRequestProperty);
        }
        return connection;
    }
}

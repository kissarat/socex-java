package socex.core.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class FormEncoder implements ComponentEncoder {
    static public final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    static private final Map<String, FormEncoder> instances = new HashMap<>();

    static public FormEncoder getInstance(String contentType){
        if (!instances.containsKey(contentType)) {
            instances.put(contentType, new FormEncoder(contentType));
        }
        return instances.get(contentType);
    }

    private final String contentType;

    public FormEncoder() {
        this(FORM_URLENCODED);
    }

    public FormEncoder(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String encode(String raw) throws UnsupportedEncodingException {
        return URLEncoder.encode(raw, getContentType());
    }

    @Override
    public String decode(String encoded) throws UnsupportedEncodingException {
        return URLEncoder.encode(encoded, getContentType());
    }
}

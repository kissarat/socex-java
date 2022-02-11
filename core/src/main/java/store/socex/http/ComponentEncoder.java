package store.socex.http;

import java.io.UnsupportedEncodingException;
import java.util.Dictionary;

public interface ComponentEncoder {
    String getContentType();
    String encode(String raw) throws UnsupportedEncodingException;
    String decode(String encoded) throws UnsupportedEncodingException;
}

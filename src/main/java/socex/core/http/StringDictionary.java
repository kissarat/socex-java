package socex.core.http;

import java.io.UnsupportedEncodingException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.function.BiConsumer;

public class StringDictionary extends Dictionary<String, String> {
    private final Dictionary<String, String> dictionary;
    public StringDictionary() {
        this(new Hashtable<>());
    }
    public StringDictionary(Dictionary<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public Enumeration<String> keys() {
        return dictionary.keys();
    }

    @Override
    public Enumeration<String> elements() {
        return dictionary.elements();
    }

    @Override
    public String get(Object key) {
        return dictionary.get(key);
    }

    public String get(String key) {
        return dictionary.get(key);
    }

    @Override
    public String put(String key, String value) {
        return dictionary.put(key, value);
    }

    public StringDictionary set(String key, String value) {
        dictionary.put(key, value);
        return this;
    }

    @Override
    public String remove(Object key) {
        return dictionary.remove(key);
    }

    public String remove(String key) {
        return dictionary.remove(key);
    }

    public static StringDictionary createSingleParameter(String key, String value) {
        var dictionary = new StringDictionary();
        dictionary.put(key, value);
        return dictionary;
    }

    public int parse(String queryString, ComponentEncoder encoder) throws UnsupportedEncodingException {
        int i = 0;
        for(String param: queryString.split("&")) {
            String[] parts = param.split("=");
            if (parts.length != 2) {
                int valuesCount = parts.length - 1;
                throw new UnsupportedEncodingException(
                        "URL parameter contains invalid number of values " + valuesCount);
            }
            put(
                    encoder.decode(parts[0]),
                    encoder.decode(parts[1])
            );
            i++;
        }
        return i;
    }

    public int parse(String queryString) throws UnsupportedEncodingException {
        return parse(queryString, FormEncoder.getInstance(FormEncoder.FORM_URLENCODED));
    }

    public StringDictionary putDefaults(Dictionary<String, String> defaults) {
        var it = defaults.keys();
        while (it.hasMoreElements()) {
            String key = it.nextElement();
            String value = get(key);
            if (null == value) {
                put(key, defaults.get(key));
            }
        }
        return this;
    }

    public void forEach(BiConsumer<String, String> consumer) {
        var it = dictionary.keys();
        while (it.hasMoreElements()) {
            String key = it.nextElement();
            consumer.accept(key, get(key));
        }
    }

    public String toString(ComponentEncoder encoder) throws UnsupportedEncodingException {
        var it = dictionary.keys();
        var builder = new StringBuilder();
        while (it.hasMoreElements()) {
            String key = it.nextElement();
            builder
                    .append(encoder.encode(key))
                    .append('=')
                    .append(encoder.encode(get(key)));
            if (it.hasMoreElements()) {
                builder.append('&');
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        try {
            return toString(FormEncoder.getInstance(FormEncoder.FORM_URLENCODED));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}

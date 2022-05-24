package socex.core.http;

public interface RequestDefaults {
    default String getPath() {
        return "";
    }

    default String getMethod() {
        return "GET";
    }

    default int getTimeout() {
        return 10000;
    }

    default StringDictionary getQuery() {
        return new StringDictionary();
    }

    default StringDictionary getHeaders() {
        return  new StringDictionary();
    }

}

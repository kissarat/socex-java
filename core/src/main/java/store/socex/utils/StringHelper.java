package store.socex.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class StringHelper {
    public static String readString(InputStream inputStream) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            var builder = new StringBuilder();
            for (String line = reader.readLine(); null != line; line = reader.readLine()) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}

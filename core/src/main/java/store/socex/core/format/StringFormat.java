package store.socex.core.format;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringFormat {
    public static String join(Stream<String> stream, String splitter ) {
        return stream.collect(Collectors.joining(splitter));
    }
    public static String join(Collection<String> lines, String splitter ) {
        final int size = lines.size();
        StringBuilder builder = new StringBuilder(size * 2);
        int i = 0;
        for(String line: lines) {
            i++;
            builder
                    .append(line);
                    if (i < size) {
                    builder.append(splitter);
                    }

        }

        return builder.toString();
    }
}

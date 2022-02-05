package store.socex.lib.format;

import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringFormat {
    public static String join(Stream<String> stream, String splitter ) {
        return stream.collect(Collectors.joining(splitter));
    }
    public static String join(Iterator<String> lines, String splitter ) {
        return lines.stream().collect(Collectors.joining(splitter));
    }
}

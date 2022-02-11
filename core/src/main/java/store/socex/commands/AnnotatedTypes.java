package store.socex.commands;

import java.lang.annotation.Annotation;
import java.util.Arrays;


public class AnnotatedTypes extends ListCommand<String> {
     static <T extends Annotation> void list(String packageName, Class<T> clazz) {

    }

    String namespace;

    @Override
    public Iterable<String> call() throws Exception {
        return Arrays.asList("a", "b");
    }
}

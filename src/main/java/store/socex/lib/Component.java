package store.socex.lib;

import java.util.stream.Stream;

public interface Component {
    default String getComponentName() { return getClass().getName(); };
//    default <Value, Name extends String> String getContextName(Context<Value, Name> ctx) { return ctx.get };
}

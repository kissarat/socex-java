package store.socex.lib;

import java.util.concurrent.RunnableFuture;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public interface Job<T extends Task<Item, Stream<Item>>, Item> extends RunnableFuture<T> {
}

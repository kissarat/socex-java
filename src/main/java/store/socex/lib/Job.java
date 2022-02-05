package store.socex.lib;

import java.util.concurrent.RunnableFuture;

public interface Job<T extends Task<ItemSource>, ItemSource> extends RunnableFuture<ItemSource> {
}

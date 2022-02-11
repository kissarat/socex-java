package store.socex.core.commands;

import java.util.concurrent.Future;

public interface Job<T extends Task<ItemSource>, ItemSource> extends Future<T> {
}

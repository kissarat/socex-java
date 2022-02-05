package store.socex.lib.commands;

import store.socex.lib.commands.Task;

import java.util.concurrent.Future;

public interface Job<T extends Task<ItemSource>, ItemSource> extends Future<T> {
}

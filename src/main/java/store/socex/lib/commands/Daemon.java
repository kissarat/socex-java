package store.socex.lib.commands;

import java.util.concurrent.Executor;
import java.util.concurrent.RunnableFuture;

public abstract class Daemon extends NestedCommand implements Executor {
    @Override
    public void execute(Runnable command) {

    }
}

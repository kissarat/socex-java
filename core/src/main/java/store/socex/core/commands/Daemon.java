package store.socex.core.commands;

import java.util.concurrent.Executor;

public abstract class Daemon extends NestedCommand implements Executor {
    @Override
    public void execute(Runnable command) {

    }
}

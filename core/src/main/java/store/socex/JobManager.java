package store.socex;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JobManager implements Consumer<Runnable>, Supplier<Runnable> {
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void accept(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public Runnable get() {
        return null;
    }
}

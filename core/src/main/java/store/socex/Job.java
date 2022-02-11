package store.socex;

import store.socex.task.FutureQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class Job implements Runnable, Consumer<Runnable> {
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void accept(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public void run() {
        for (var cmd = queue.poll(); cmd != null; cmd = queue.poll()) {
            cmd.run();
        }
    }
}

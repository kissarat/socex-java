package store.socex.task;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class FutureQueue<T, R> implements Function<T, R>, Future<R>, Consumer<T>, Runnable {
    private final Queue<T> queue;
    private Throwable error;

    public FutureQueue(T task) {
        this(createQueue(task));
    }

    public static <T> Queue<T> createQueue(T task) {
        var queue = new ConcurrentLinkedQueue<T>();
        queue.add(task);
        return queue;
    }

    public FutureQueue(Queue<T> queue) {
        this.queue = queue;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        error = new InterruptedException();
        return true;
    }

    @Override
    public boolean isCancelled() {
        return error != null;
    }

    @Override
    public boolean isDone() {
        return queue.isEmpty();
    }

    public R get() throws InterruptedException, ExecutionException {
        var task = queue.poll();
        if (task == null) {
            return null;
        }
        return apply(task);
    }

    @Override
    public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return get();
    }

    @Override
    public void accept(T task) {
        synchronized (queue) {
            queue.add(task);
            if (queue.size() <= 1) {

            }
        }
    }
}

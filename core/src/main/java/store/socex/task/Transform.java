package store.socex.task;

import java.util.Queue;

public abstract class Transform<T, R> extends FutureQueue<T, R> {
    public Transform(T task) {
        super(createQueue(task));
    }

    public Transform(Queue<T> queue) {
        super(queue);
    }
}

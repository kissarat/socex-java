package store.socex;

import java.util.concurrent.Executor;

public class Lord implements Executor {
    private static final Lord instance = new Lord();

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public void list(Iterable<Object> items) {
        for(var item: items) {
            System.out.println(item.toString());
        }
    }

    public static Lord getInstance() {
        return instance;
    }

    static Object resolve(String name) {
        return Registry.getInstance().resolve(name);
    }

    public static void main(String[] args) {
        var executor = (Executor) resolve(Registry.EXECUTOR);
        var runnable = (Runnable) resolve(Registry.RUN);
        executor.execute(runnable);
    }
}

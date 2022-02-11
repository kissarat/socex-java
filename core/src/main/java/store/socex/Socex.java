package store.socex;

import java.util.ArrayList;
import java.util.Collection;

public class Socex implements Runnable {
    protected final Runnable main;
    public Socex(Runnable main) {
        this.main = main;
    }
    protected static Collection<Runnable> instances = new ArrayList<>();
    public static <T extends Runnable> void create(T instance) {
        instances.add(instance);
    }

    @Override
    public void run() {
        this.main.run();
    }
}

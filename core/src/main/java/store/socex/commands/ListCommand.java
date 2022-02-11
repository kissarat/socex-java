package store.socex.commands;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

public abstract class ListCommand<T> implements Callable<Iterable<T>>, Runnable {
    @Override
    public void run() {
        try {
            for(var line: call()) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

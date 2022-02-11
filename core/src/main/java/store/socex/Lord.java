package store.socex;

import java.lang.reflect.InvocationTargetException;

public class Lord {
    public static void main(String[] args) {
        try {
            var mainClass = Class.forName(System.getProperty("run"));
            var constructor = mainClass.getConstructor();
            Runnable runnable = (Runnable) constructor.newInstance();
            var app = new Socex(runnable);
            app.run();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

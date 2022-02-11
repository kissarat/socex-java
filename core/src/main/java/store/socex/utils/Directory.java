package store.socex.utils;

import store.socex.task.FutureQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Directory extends FutureQueue<File, Collection<File>> implements Callable<Collection<File>>, Consumer<File>, Runnable {
    public Directory(File dir) {
        super(dir);
    }

    @Override
    public Collection<File> apply(File dir) {
        Collection<File> files = new ArrayList<>();
        var entries = dir.listFiles();
        if (entries != null) {
            for(var entry: entries) {
                if (entry.isDirectory()) {
                    accept(entry);
                } else {
                    files.add(entry);
                }
            }
        }
        return files;
    }

    @Override
    public Collection<File> call() throws Exception {
        Collection<File> allFiles = new ArrayList<>();
        for (var files = get(); files != null; files = get()) {
            allFiles.addAll(files);
        }
        return allFiles;
    }

    public static Collection<File> recursiveFiles(File file) throws Exception {
        var dir = new Directory(file);
        return dir.call();
    }

    @Override
    public void run() {
        try {
            call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

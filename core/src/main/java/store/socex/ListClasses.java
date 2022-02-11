package store.socex;

import store.socex.stereotype.Entrypoint;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@Entrypoint("store.socex")
public class ListClasses implements Runnable, FileFilter {
    ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    Collection<String> lookup(File dir) {
        var list = new ArrayList<String>();
        var files = dir.listFiles(this);
        if (null == files) {
            System.err.println(dir.getAbsolutePath() + " is null");
        } else {
            for(var file: files) {
                if (file.isDirectory()) {
                    list.addAll(lookup(file));
                } else {
                    list.add(file.getName().replace(".class", ""));
                }
            }
        }
        return list;
    }

    @Override
    public void run() {
        var packageName = "store.socex";
        var loader = getClassLoader();
        try {
            var it = loader.getResources(packageName.replace('.', '/'));
            if (it == null) {
                System.err.println("No InputStream");
                System.exit(1);
            }
            var list = new ArrayList<String>();
            while (it.hasMoreElements()) {
                list.addAll(lookup(new File(it.nextElement().getFile())));
            }
            for(var className: list) {
                System.out.println(className);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean accept(File file) {
        System.out.println(file.getName());
        return file.isDirectory() || file.getName().endsWith(".class");
    }
}

package socex.morozov;

import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class App 
{
    public static void printLines(List<String> lines) {
        Collections.sort(lines);
        for(String line: lines) {
            if (line.length() > 0) {
                System.out.println(line);
            }
        }
    }

    public static <K, V>void printEntrySet(Set<Map.Entry<K, V>> entrySet) {
        final List<String> lines = new ArrayList<String>();
        for(Map.Entry<?, ?> entry: entrySet) {
            lines.add(entry.getKey() + "=" + entry.getValue());
        }
        printLines(lines);
    }

    public static void showProperties() {
        printEntrySet(System.getProperties().entrySet());
    }

    public static void showEnvironment() {
        printEntrySet(System.getenv().entrySet());
    }

    public static List<String> collectPreferences(Preferences prefs) throws BackingStoreException {
        final List<String> lines = new ArrayList<String>();
        for(String key: prefs.keys()) {
            lines.add(String.format("%s=%s", key, prefs.get(key, "")));
        }
        for(String name: prefs.childrenNames()) {
            for (String line: collectPreferences(prefs.node(name))) {
                lines.add(String.format("%s.%s", name, line));
            }
        }
        return lines;
    }

    public static void showPreferences(Preferences prefs) throws BackingStoreException {
        final List<String> lines = collectPreferences(prefs);
        printLines(lines);
    }

    public static void main( String[] args )
    {
        String section = args.length > 0 ? args[0] : "";
        if (section.equals("environment")) {
            showEnvironment();
        } else if (section.equals("properties")) {
            showProperties();
        } else {
            System.out.println("# Properties");
            showProperties();
            System.out.println("\n# Environment");
            showEnvironment();
            System.out.println("\n# System preferences");
            try {
                showPreferences(Preferences.systemRoot());
                System.out.println("\n# User preferences");
                showPreferences(Preferences.userRoot());
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }
        }
    }
}

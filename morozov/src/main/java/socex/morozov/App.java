package socex.morozov;

import java.util.*;

public class App 
{
    public static void showProperties() {
        final Properties env = System.getProperties();
        final List<String> lines = new ArrayList<String>();
        for(Map.Entry<?, ?> entry: env.entrySet()) {
            lines.add(entry.getKey() + "=" + entry.getValue());
        }
        Collections.sort(lines);
        for(String line: lines) {
            if (line.length() > 0) {
                System.out.println(line);
            }
        }
    }

    public static void showEnvironment() {
        final Map<?, ?> env = System.getenv();
        final List<String> lines = new ArrayList<String>();
        for(Map.Entry<?, ?> entry: env.entrySet()) {
            lines.add(entry.getKey() + "=" + entry.getValue());
        }
        Collections.sort(lines);
        for(String line: lines) {
            if (line.length() > 0) {
                System.out.println(line);
            }
        }
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
            System.out.println("# Environment");
            showEnvironment();
        }
    }
}

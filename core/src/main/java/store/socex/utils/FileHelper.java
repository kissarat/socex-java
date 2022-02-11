package store.socex.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileHelper {
    public static String readContent(Path filename)  throws IOException{
        final int size = (int)Files.size(filename);
        FileReader reader = new FileReader(filename.toString());
        char[] buffer = new char[size];
        reader.read(buffer);
        reader.close();
        return String.valueOf(buffer);
    }

    public static String tryReadContent(Path filename, String defaultValue) {
        if (!Files.exists(filename)) {
            return defaultValue;
        }
        try {
            return readContent(filename);
        } catch (IOException exception) {
            return defaultValue;
        }
    }

    public static JSONObject safeReadJSONObject(Path filename) {
        final String source = tryReadContent(filename, "{}");
        try {
            return new JSONObject(source);
        } catch(JSONException exception) {
            return new JSONObject();
        }
    }

    public static Map<String, String> safeReadMap(Path filename, Map<String, String> map) {
        JSONObject json = safeReadJSONObject(filename);
        if (null == map) {
            map = new HashMap<>();
        }
        for(Entry<String,?> entry: json.toMap().entrySet()) {
            final Object value = entry.getValue();
            map.put(entry.getKey(), value.toString());
        }
        return map;
    }

    public static void writeContent(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }

    public static void writeJSONObject(String filename, JSONObject obj) throws IOException {
        writeContent(filename, obj.toString());
    }

    public static void writeMap(String filename, Map<String, String> map) throws IOException {
        JSONObject dict = new JSONObject();
        for (Entry<String, String> entry : map.entrySet()) {
            dict.put(entry.getKey(), entry.getValue());
        }
        writeJSONObject(filename, dict);
    }
}

package store.socex.factor.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileSerialRegistry {
    protected List<Entry> entries = new ArrayList<>();
    protected int number = 0;
    public int add(Entry entry) {
        synchronized (entries) {
            int n = ++number;
            entry.id = n;
            entries.add(entry);
            return n;
        }
    }

    public Entry get(int i) {
        return  entries.get(i);
    }
}

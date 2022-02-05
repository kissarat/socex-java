package store.socex.lib;

import java.util.stream.BaseStream;
import java.util.stream.Stream;

public interface Task<Item, S extends BaseStream<Item, S>> extends BaseStream<Item, S> {
}

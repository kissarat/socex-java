package store.socex.lib.commands;

import store.socex.lib.Component;

public interface Task<ItemSource> extends Component, Command {
    ItemSource getSource();
}

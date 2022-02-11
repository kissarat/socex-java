package store.socex.core.commands;

import store.socex.core.Component;

public interface Task<ItemSource> extends Component, Command {
    ItemSource getSource();
}

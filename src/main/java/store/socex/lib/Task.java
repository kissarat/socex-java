package store.socex.lib;

public interface Task<ItemSource> extends Component {
    ItemSource getSource();
}

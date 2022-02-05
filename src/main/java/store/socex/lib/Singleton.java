package store.socex.lib;

public interface Singleton extends Component {
    default String getRegistryName() {
        return getClass().getName();
    }
}

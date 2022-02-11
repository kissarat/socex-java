package store.socex.core;

public interface Singleton extends Component {
    default String getRegistryName() {
        return getClass().getName();
    }
}

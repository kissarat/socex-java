package store.socex.lib.lambda;

public class Jo<Left extends Joint<L, R>, Right extends Joint<L, R>, L extends Joint<?,?>, R extends Joint<Left,Right>> implements Joint<Left, Right> {
    private final boolean direct;
    private final Right right;

    public Jo(Left left, Right right) {
        direct =
    }

    protected static <T> boolean equals(T first, T second) {
        return first.equals(second);
    }

    public boolean is(Left left, Right right) {
        this.left.is(left, right);
    }

    @Override
    public boolean of(Right right, Left left) {
        return false;
    }
}

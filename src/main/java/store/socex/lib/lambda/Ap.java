package store.socex.lib.lambda;

public class Ap<L, R extends Assign<L, R>> implements Assign<L, R> {
    private final L left;
    private final R right;

    public Ap(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean is(L left, R right) {
        return right.of(this.left) == this.right.of(left);
    }

    @Override
    public R of(L left) {
        return null;
    }
}

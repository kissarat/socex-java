package store.socex.lib.lambda;

public interface Joint<Left, Right> {
    boolean is(Left left, Right right);
    boolean of(Right right, Left left);
}

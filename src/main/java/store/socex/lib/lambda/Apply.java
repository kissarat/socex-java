package store.socex.lib.lambda;

public interface Apply<Group extends Apply<Group, Subject>, Subject> {
    Group is(Subject right);
    Subject of(Group left);
}

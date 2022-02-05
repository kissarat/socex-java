package store.socex.lib.lambda;

public interface Assign<Setting, Claim extends Assign<Setting, Claim>> {
    Setting is(Claim right);
    Claim of(Setting left);
}

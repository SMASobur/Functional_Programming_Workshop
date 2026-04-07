package se.lexicon;

public interface SubscriberFilter {
    boolean matches(Subscriber subscriber);
}

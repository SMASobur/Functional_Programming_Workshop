package se.lexicon;

public class BusinessActions {
    // Default extension of 12 months
    public static SubscriberAction extendSubscription() {
        return extendSubscription(12);
    }

     //6: Extend Subscription
    public static SubscriberAction extendSubscription (int months){
        return subscriber -> subscriber.extendSubscription(months);
    }

    // 7. Deactivate Subscriber
    public static SubscriberAction deactivateSubscriber(){
        return subscriber -> subscriber.setActive(false);
    }


}

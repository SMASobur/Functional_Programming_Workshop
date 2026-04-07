package se.lexicon;

public class BusinessFilters {

    // 1. Active Subscriber
    public static SubscriberFilter isActive(){
        return subscriber -> subscriber.isActive();
    }

    // 2: Expiring Subscription (0 or 1 month remaining)
    public static SubscriberFilter isExpiring(){
        return subscriber -> subscriber.getMonthsRemaining() <= 1;
    }
    // 3: Active AND Expiring Subscriber
    public static SubscriberFilter isActiveAndExpiring(){
        return subscriber -> subscriber.isActive() && subscriber.getMonthsRemaining() <=1;
    }

    // 4: Subscriber by Plan
    public static SubscriberFilter byPlan(Plan plan){
        return subscriber -> subscriber.getPlan() == plan;
    }
    // 5: Paying Subscriber (BASIC or PRO)
    public static SubscriberFilter isPaying(){
        return subscriber -> subscriber.getPlan() == Plan.PRO || subscriber.getPlan() == Plan.BASIC;
    }
}

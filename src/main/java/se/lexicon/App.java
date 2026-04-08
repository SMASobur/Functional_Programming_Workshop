package se.lexicon;

import java.util.List;

public class App {
    static void main() {
        //Setup DAO and processor

        SubscriberDAO dao = new SubscriberDAO();
        SubscriberProcessor processor= new SubscriberProcessor();

        // Create  subscribers
        Subscriber Sikdar = new Subscriber(1, "sikdar@email.com", Plan.PRO, true, 5);
        Subscriber Ragavi = new Subscriber(2, "ragavi@email.com", Plan.BASIC, true, 0);
        Subscriber Muthana = new Subscriber(3, "muthana@email.com", Plan.FREE, true, 1);
        Subscriber Shanmu = new Subscriber(4, "shanmu@email.com", Plan.PRO, false, 3);

        dao.save(Sikdar);
        dao.save(Ragavi);
        dao.save(Muthana);
        dao.save(Shanmu);

        List<Subscriber> all = dao.findAll();

        //Scenario 1: Show Active Subscribers
        System.out.println("===Active Subscribers===");
        //System.out.println(all);
        List<Subscriber> active = processor.findSubscribers(all, BusinessFilters.isActive());
        active.forEach(x -> System.out.println(x));

        //Scenario 2: Show Expiring Subscriptions
        System.out.println("\n=== Expiring Subscriptions ===");
        List<Subscriber> expiring = processor.findSubscribers(all, BusinessFilters.isExpiring());
        expiring.forEach(x-> System.out.println(x));

        // Scenario 3: Show Active and Expiring Subscribers
        System.out.println("\n=== Active and Expiring Subscriptions ===");
        List<Subscriber> activeAndExpiring = processor.findSubscribers(all, BusinessFilters.isActiveAndExpiring());
        expiring.forEach(x-> System.out.println(x));

        //Scenario 5: Deactivate Expired Free Subscribers
        System.out.println("\n=== Deactivating Expired Free Subscribers ===");
        SubscriberFilter expiredFree = subscriber ->
                subscriber.getPlan() == Plan.FREE && subscriber.getMonthsRemaining() == 0;
        processor.applyToMatching(all, expiredFree, BusinessActions.deactivateSubscriber());

        //Scenario 6: Filter Subscribers by Plan
        System.out.println("\n=== PRO Subscribers ===");
        List<Subscriber> pro = processor.findSubscribers(all, BusinessFilters.byPlan(Plan.PRO));
        pro.forEach(x-> System.out.println(x));

        // Scenario 7: Find paid subscribers
        System.out.println("\n=== Paying Subscribers ===");
        List<Subscriber> paying = processor.findSubscribers(all, BusinessFilters.isPaying());
        paying.forEach(x-> System.out.println(x));

        // Show final state
        System.out.println("\n=== Final State ===");
        dao.findAll().forEach(System.out::println);

    }
}

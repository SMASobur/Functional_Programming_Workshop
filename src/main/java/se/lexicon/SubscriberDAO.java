package se.lexicon;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscriberDAO {
    private final Map<Integer, Subscriber> storage = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // Save
    public void save(Subscriber subscriber) {
        if (subscriber.getId() == 0) {
            int newId = idGenerator.getAndIncrement();

            Subscriber newSubscriber = new Subscriber(newId, subscriber.getEmail(),
                    subscriber.getPlan(), subscriber.isActive(), subscriber.getMonthsRemaining());
            storage.put(subscriber.getId(), subscriber);
        }else {
            storage.put(subscriber.getId(), subscriber);
        }
    }

    // Find All

    public List<Subscriber> findAll(){
        return new ArrayList<>(storage.values());
    }

    // Find by ID

    public Optional<Subscriber> findById (int id){
        return Optional.ofNullable(storage.get(id));
    }
}

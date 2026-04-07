package se.lexicon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    // Find subscribers matching
    public List<Subscriber> findSubscribers ( List<Subscriber> list, SubscriberFilter filter){
        return list.stream()
                .filter(subscriber -> filter.matches(subscriber))
                .collect(Collectors.toList());
    }

    // Apply action to matching subscribers

    public List<Subscriber> applyToMatching( List<Subscriber> list, SubscriberFilter filter, SubscriberAction action){
        List<Subscriber> matching = findSubscribers(list, filter);
        matching.forEach(subscriber -> action.run(subscriber));
        return new ArrayList<>(list);
    }
}

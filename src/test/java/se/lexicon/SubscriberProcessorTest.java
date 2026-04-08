package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Subscription Management System Tests")
public class SubscriberProcessorTest {
    private SubscriberProcessor processor;
    private SubscriberDAO dao;
    private List<Subscriber> testSubscribers;

    @BeforeEach
    void setUp(){
        processor = new SubscriberProcessor();
        dao = new SubscriberDAO();
        // Initialize with test data
        testSubscribers = Arrays.asList(
                new Subscriber(1, "sikdar@email.com", Plan.PRO, true, 5),
                new Subscriber(2, "ragavi@email.com", Plan.BASIC, true, 0),
                new Subscriber(3, "muthana@email.com", Plan.FREE, true, 1),
                new Subscriber(4, "shanmu@email.com", Plan.PRO, false, 3),
                new Subscriber(5, "sikdar1@email.com", Plan.PRO, true, 5),
                new Subscriber(6, "ragavi1@email.com", Plan.BASIC, true, 0),
                new Subscriber(7, "muthana1@email.com", Plan.FREE, true, 1),
                new Subscriber(8, "shanmu1@email.com", Plan.PRO, false, 3)

        );
        // Save all to DAO
        testSubscribers.forEach(dao::save);
    }
    @Nested
    @DisplayName("Scenario 1: Show Active Subscribers")
    class ActiveSubscribersTests{
        @Test
        @DisplayName( "Should find all active subscribers")
        void shouldFindActiveSubscribers(){
            List<Subscriber> all = dao.findAll();
            List<Subscriber> active = processor.findSubscribers(all, BusinessFilters.isActive());

            assertEquals(6, active.size(), "Should have 6 active subscribers");

            // Verify Active subscribers
            assertTrue(active.stream().allMatch(Subscriber::isActive));

            // Verify specific active subscribers
            List<String> activeEmails = active.stream()
                    .map(Subscriber::getEmail)
                    .toList();

            assertAll("Active subscribers should include",
                    () -> assertTrue(activeEmails.contains("sikdar@email.com")),
                    () -> assertTrue(activeEmails.contains("ragavi@email.com")),
                    () -> assertTrue(activeEmails.contains("muthana@email.com")),
                    () -> assertTrue(activeEmails.contains("sikdar1@email.com")),
                    () -> assertTrue(activeEmails.contains("ragavi1@email.com")),
                    () -> assertTrue(activeEmails.contains("muthana1@email.com"))
            );
            // Verify inactive subscribers
            assertFalse(activeEmails.contains("shanmu@email.com"));
            assertFalse(activeEmails.contains("shanmu1@email.com"));



        }
    }

}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    private EventLog el;
    private List<Event> log;
    private Movie m1;

    @BeforeEach
    public void setUp() {
        m1 = new Movie("Pulp Fiction", "January 1st 2010", 5,
                "A very cool movie starring John Travolta.");
        log = new ArrayList<>();
        el = EventLog.getInstance();
        el.clear();
    }

    @Test
    public void testSetName() {
        String name = "Watson";
        m1.setName(name);
        assertEquals(name, m1.getName());

        for (Event next : el) {
            log.add(next);
        }
        assertEquals(log.get(0).getDescription(), "Event log cleared.");
        assertEquals(log.get(1).getDescription(), "Name of movie set to: Watson.");
    }

    @Test
    public void testSetRating() {
        int rating = 1;
        m1.setRating(rating);
        assertEquals(rating, m1.getRating());

        for (Event next : el) {
            log.add(next);
        }
        assertEquals(log.get(0).getDescription(), "Event log cleared.");
        assertEquals(log.get(1).getDescription(), "Rating of movie set to: 1.");
    }

    @Test
    public void testSetDate() {
        String date = "22nd july 2022";
        m1.setDate(date);
        assertEquals(date, m1.getDate());

        for (Event next : el) {
            log.add(next);
        }
        assertEquals(log.get(0).getDescription(), "Event log cleared.");
        assertEquals(log.get(1).getDescription(), "Date of movie set to: 22nd july 2022.");
    }

    @Test
    public void testSetDescription() {
        String description = "About a dude";
        m1.setDescription(description);
        assertEquals(description, m1.getDescription());

        for (Event next : el) {
            log.add(next);
        }
        assertEquals(log.get(0).getDescription(), "Event log cleared.");
        assertEquals(log.get(1).getDescription(), "Description of movie set to: About a dude.");
    }
}

package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieTrackerTest {
    private EventLog el;
    private List<Event> log;
    private MovieTracker testTracker;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private Movie m4;
    private Movie m5;

    @BeforeEach
    public void setUp() {
        testTracker = new MovieTracker("Shaun");

        m1 = new Movie("Pulp Fiction", "January 1st 2010", 5,
                "A very cool movie starring John Travolta." );
        m2 = new Movie("Gone Girl", "February 2nd 2011", 4,
                "A strange movie starring Ben Affleck." );
        m3 = new Movie("Interstellar", "March 3rd 2012", 4,
                "An incredible movie starring Matthew McConaughey." );
        m4 = new Movie("The Martian", "April 4th 2013", 3,
                "A very interesting movie starring Matt Damon." );
        m5 = new Movie("Get Out", "May 5th 2014", 2,
                "A very disturbing movie starring Daniel Kaluuya." );

        testTracker.addMovie(m1);
        testTracker.addMovie(m2);
        testTracker.addMovie(m3);
        testTracker.addMovie(m4);
        testTracker.addMovie(m5);

        log = new ArrayList<>();
        el = EventLog.getInstance();
        el.clear();
    }

    @Test
    public void testConstructor() {
        assertEquals(testTracker.getUserName(), "Shaun");
    }

    @Test
    public void testAddMovie() {
        assertEquals(testTracker.getNumMovies(), 5);
        Movie testMovie = new Movie("The Conjuring", "June 6th 2015", 3,
                "A horror movie starring Vera Farmiga." );
        testTracker.addMovie(testMovie);
        assertEquals(testTracker.getNumMovies(), 6);
        assertTrue(testTracker.containsMovie(testMovie));

        for (Event next : el) {
            log.add(next);
        }
        assertEquals(log.get(0).getDescription(), "Event log cleared.");
        assertEquals(log.get(1).getDescription(), "Added a movie to the movie tracker.");
    }

    @Test
    public void testGetMovieNames() {
        List<String> expected = Arrays.asList("Pulp Fiction", "Gone Girl", "Interstellar", "The Martian", "Get Out");
        assertEquals(testTracker.getMovieNames(), expected);

        Movie testMovie = new Movie("The Conjuring", "June 6th 2015", 3,
                "A horror movie starring Vera Farmiga." );
        testTracker.addMovie(testMovie);
        List<String> updatedExpected = Arrays.asList("Pulp Fiction", "Gone Girl", "Interstellar",
                "The Martian", "Get Out", "The Conjuring");
        assertEquals(testTracker.getMovieNames(), updatedExpected);

    }

    @Test
    public void testGetMovieDate() {
        assertEquals(testTracker.getMovieDate("Pulp Fiction"), "January 1st 2010");
        Movie testMovie = new Movie("The Conjuring", "June 6th 2015", 3,
                "A horror movie starring Vera Farmiga." );
        testTracker.addMovie(testMovie);
        assertEquals(testTracker.getMovieDate("The Conjuring"), "June 6th 2015");
    }

    @Test
    public void testGetMovieRating() {
        assertEquals(testTracker.getMovieRating("Pulp Fiction"), "5");
        Movie testMovie = new Movie("The Conjuring", "June 6th 2015", 3,
                "A horror movie starring Vera Farmiga." );
        testTracker.addMovie(testMovie);
        assertEquals(testTracker.getMovieRating("The Conjuring"), "3");
    }

    @Test
    public void testGetMovieDescription() {
        assertEquals(testTracker.getMovieDescription("Pulp Fiction"),
                "A very cool movie starring John Travolta.");
        Movie testMovie = new Movie("The Conjuring", "June 6th 2015", 3,
                "A horror movie starring Vera Farmiga." );
        testTracker.addMovie(testMovie);
        assertEquals(testTracker.getMovieDescription("The Conjuring"),
                "A horror movie starring Vera Farmiga.");
    }

    @Test
    public void testMoviesToJson() {
        JSONArray jsonArray = testTracker.moviesToJson();
        assertEquals(jsonArray.length(),5);
    }
}
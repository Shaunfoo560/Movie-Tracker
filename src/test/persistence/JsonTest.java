package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Based on the supplied Workroom example for CPSC 210
public class JsonTest {
    protected void checkMovie(String name, String date, int rating, String description, Movie movie) {
        assertEquals(name, movie.getName());
        assertEquals(date, movie.getDate());
        assertEquals(rating, movie.getRating());
        assertEquals(description, movie.getDescription());
    }
}

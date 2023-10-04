package persistence;

import model.Movie;
import model.MovieTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based on the supplied Workroom example for CPSC 210
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            MovieTracker mt = new MovieTracker("Shaun");
            JsonWriter writer = new JsonWriter("./data/my\\\bad:file:name.json");
            writer.open();
            fail("IOException should have triggered");
        } catch (IOException e) {
            // all good!
        }
    }

    @Test
    void testWriterEmptyMovieTracker() {
        try {
            MovieTracker mt = new MovieTracker("Shaun");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieTracker.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieTracker.json");
            mt = reader.read();
            assertEquals("Shaun", mt.getUserName());
            assertEquals(0, mt.getNumMovies());
        } catch (IOException e) {
            fail("Exception shouldn't have triggered");
        }
    }

    @Test
    void testWriterSomeMoviesInMovieTracker() {
        try {
            MovieTracker mt = new MovieTracker("Shaun");
            mt.addMovie(new Movie("Ironman", "4th April 2013", 1,
                    "Man made of iron"));
            mt.addMovie(new Movie("The Flash", "5th May 2014", 2,
                    "Man who is fast"));
            mt.addMovie(new Movie("Daredevil", "6th June 2015", 3,
                    "Blind man"));
            JsonWriter writer = new JsonWriter("./data./testWriterSomeMoviesInMovieTracker.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSomeMoviesInMovieTracker.json");
            mt = reader.read();
            assertEquals("Shaun", mt.getUserName());
            List<Movie> movies = mt.getMovies();
            assertEquals(3, movies.size());
            checkMovie("Ironman", "4th April 2013", 1, "Man made of iron", movies.get(0));
            checkMovie("The Flash", "5th May 2014", 2, "Man who is fast", movies.get(1));
            checkMovie("Daredevil", "6th June 2015", 3, "Blind man", movies.get(2));
        } catch (IOException e) {
            fail("Exception shouldn't have triggered");
        }
    }
}

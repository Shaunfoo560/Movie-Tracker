package persistence;

import model.Movie;
import model.MovieTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based on the supplied Workroom example for CPSC 210
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            MovieTracker mt = reader.read();
            fail("IOException should have triggered");
        } catch (IOException e) {
            // all done!
        }
    }

    @Test
    void testReaderEmptyMovieTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieTracker.json");
        try {
            MovieTracker mt = reader.read();
            assertEquals("Shaun", mt.getUserName());
            assertEquals(0, mt.getNumMovies());
        } catch (IOException e) {
            fail("Couldn't read file");
        }
    }

    @Test
    void testReaderSomeMoviesInMovieTracker() {
        JsonReader reader = new JsonReader("./data/testReaderSomeMoviesInMovieTracker.json");
        try {
            MovieTracker mt = reader.read();
            assertEquals("Shaun", mt.getUserName());
            List<Movie> movies = mt.getMovies();
            assertEquals(3, movies.size());
            checkMovie("Spiderman", "1st January 2010", 3, "Man gets bitten by a spider",
                    movies.get(0));
            checkMovie("Batman", "2nd February 2011", 4, "Man is a bat", movies.get(1));
            checkMovie("Superman", "3rd March 2012", 5, "Man who is super", movies.get(2));
        } catch (IOException e) {
            fail("Couldn't read file");
        }
    }
}

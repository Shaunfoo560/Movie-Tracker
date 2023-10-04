package persistence;

import model.Movie;
import model.MovieTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Based on the supplied Workroom example for CPSC 210
// Represents a json reader that can access movie tracker data in the JSON file
public class JsonReader {
    private String saved;

    // EFFECTS: initializes json reader to be able to read from source file
    public JsonReader(String source) {
        this.saved = source;
    }

    // EFFECTS: reads movie tracker file and returns data
    // throws IOException if an error occurs while reading data from file
    public MovieTracker read() throws IOException {
        String jsonData = readFile(saved);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieTracker(jsonObject);
    }

    // EFFECTS: turns source file into a stream of strings, reading it and returning it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    // EFFECTS: parses movie tracker list from JSON object and then returns the object
    private MovieTracker parseMovieTracker(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        MovieTracker mt = new MovieTracker(username);
        addMovies(mt, jsonObject);
        return mt;
    }

    // MODIFIES: mt
    // EFFECTS: parses movies from JSON object and adds them to the movie tracker list
    private void addMovies(MovieTracker mt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(mt, nextMovie);
        }
    }

    // MODIFIES: mt
    // EFFECTS: parses movie from JSON object and adds it to movie tracker list
    private void addMovie(MovieTracker mt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        int rating = jsonObject.getInt("rating");
        String description = jsonObject.getString("description");
        Movie movie = new Movie(name, date, rating, description);
        mt.addMovie(movie);
    }
}

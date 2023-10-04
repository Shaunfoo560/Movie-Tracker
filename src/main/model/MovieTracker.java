package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Recordable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of movies that have been added to the list associated with userName
public class MovieTracker implements Recordable {
    List<Movie> movies = new ArrayList<>();
    private String userName;

    // MODIFIES: this
    // EFFECTS: Creates user profile
    public MovieTracker(String name) {
        this.userName = name;
    }

    // EFFECTS: returns user profile name
    public String getUserName() {
        return userName;
    }

    // EFFECTS: returns list of movies in the movie tracker currently
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    // EFFECTS: Adds a movie entry into the list of movies
    public void addMovie(Movie m) {
        movies.add(m);
        EventLog.getInstance().logEvent(new Event("Added a movie to the movie tracker."));
    }

    // EFFECTS: returns the number of movies added to the list of movies
    public int getNumMovies() {
        return movies.size();
    }

    // EFFECTS: returns true if the list of movies contains the movie
    public boolean containsMovie(Movie m) {
        return movies.contains(m);
    }

    // EFFECTS: returns a list of all the movie names in your list of movies
    public List<String> getMovieNames() {
        List<String> movieNames = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            movieNames.add(movies.get(i).getName());
        }
        return movieNames;
    }

    // EFFECTS: returns the date that you watched a chosen movie
    public String getMovieDate(String name) {
        String date = "";
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(name)) {
                date = movies.get(i).getDate();
            }
        }
        return date;
    }

    // EFFECTS: returns the rating that you gave a chosen movie
    public String getMovieRating(String name) {
        String rating = "";
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(name)) {
                rating = String.valueOf(movies.get(i).getRating());
            }
        }
        return rating;
    }

    // EFFECTS: returns the description of a chosen movie
    public String getMovieDescription(String name) {
        String description = "";
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(name)) {
                description = movies.get(i).getDescription();
            }
        }
        return description;
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", userName);
        json.put("movies", moviesToJson());
        return json;
    }

    // EFFECTS: returns movies in this movie tracker list as a JSON array
    public JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movies) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}

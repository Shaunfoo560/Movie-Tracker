package model;

import org.json.JSONObject;
import persistence.Recordable;

import java.util.ArrayList;
import java.util.List;

// Represents a movie having a name, date, rating, and a description
public class Movie implements Recordable {
    private String name;
    private String date;
    private int rating;
    private String description;

    // MODIFIES: this
    // EFFECTS: Adds a movie name, date, rating, and description to the movie entry
    public Movie(String movieName, String movieDate, int movieRating, String movieDesc) {
        this.name = movieName;
        this.date = movieDate;
        this.rating = movieRating;
        this.description = movieDesc;
    }

    // EFFECTS: return name of movie
    public String getName() {
        return name;
    }

    // EFFECTS: return date user watched the movie
    public String getDate() {
        return date;
    }

    // EFFECTS: return rating (1-5) that user gave the movie
    public int getRating() {
        return rating;
    }

    // EFFECTS: return a description of the movie
    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of a movie
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Name of movie set to: " + name + "."));
    }

    // MODIFIES: this
    // EFFECTS: sets the date of a movie
    public void setDate(String date) {
        this.date = date;
        EventLog.getInstance().logEvent(new Event("Date of movie set to: " + date + "."));
    }

    // MODIFIES: this
    // EFFECTS: sets the rating of a movie
    public void setRating(int rating) {
        this.rating = rating;
        EventLog.getInstance().logEvent(new Event("Rating of movie set to: " + rating + "."));
    }

    // MODIFIES: this
    // EFFECTS: sets the description of a movie
    public void setDescription(String description) {
        this.description = description;
        EventLog.getInstance().logEvent(new Event("Description of movie set to: " + description + "."));
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("rating", rating);
        json.put("description", description);
        return json;
    }
}

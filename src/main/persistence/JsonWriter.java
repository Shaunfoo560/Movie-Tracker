package persistence;

import model.MovieTracker;
import org.json.JSONObject;

import java.io.*;

// Based on the supplied Workroom example for CPSC 210
// Represents a json writer that writes movie tracker data to file in JSON representation
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: initializes the writer and throws FileNotFoundException if the end file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: converts movie tracker data to JSON representation in file
    public void write(MovieTracker mt) {
        JSONObject json = mt.toJson();
        saveToFile(json.toString(2));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}

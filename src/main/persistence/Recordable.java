package persistence;

import org.json.JSONObject;

// Based on the supplied Workroom example for CPSC 210
// Represents an interface that is implemented by Movie and MovieTracker
public interface Recordable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

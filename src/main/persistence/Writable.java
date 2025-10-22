package persistence;

import org.json.JSONObject;

// Source: JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

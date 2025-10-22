package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Dealership;

// Source: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Dealership read() throws IOException {
        return null; // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; // stub
    }

    // EFFECTS: parses Dealership from JSON object and returns it
    private Dealership parseWorkRoom(JSONObject jsonObject) {
        return null; // stub
    }

    // MODIFIES: wr
    // EFFECTS: parses Car from JSON object and adds them to Dealership
    private void addThingies(Dealership wr, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: wr
    // EFFECTS: parses Car from JSON object and adds it to Dealership
    private void addThingy(Dealership wr, JSONObject jsonObject) {
        // stub
    }
}

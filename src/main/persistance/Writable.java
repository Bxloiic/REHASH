package main.persistance;

import org.json.*;

public interface Writable { 
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}

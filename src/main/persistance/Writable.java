package main.persistance;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.json.*;

public interface Writable { 
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}

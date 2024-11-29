package main.persistance;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import java.io.*;
import java.util.*;
import org.json.*;

import main.model.Hash;
import main.model.Hashdex;
import main.model.Outfit;

//Writes the data to be stored into a file
public class JsonWriter {
    // fields
    private static final int TAB = 4;
    private PrintWriter writer;// reads data then writes it into a file
    private String file;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String file) {
        this.file = file;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of hashes, hashdexes, and outfits to file
    public void write(List<Writable> writables) throws IOException {
        JSONObject jsonObject = new JSONObject();

        JSONArray hashdexesJson = new JSONArray();
        JSONArray hashesJson = new JSONArray();
        JSONArray outfitsJson = new JSONArray();

        for (Writable writable : writables) {
            if (writable instanceof Hashdex) {
                hashdexesJson.put(writable.toJson());
            } else if (writable instanceof Hash) {
                hashesJson.put(writable.toJson());
            } else if (writable instanceof Outfit) {
                outfitsJson.put(writable.toJson());
            }
        }

        jsonObject.put("hashdexes", hashdexesJson);
        jsonObject.put("hashes", hashesJson);
        jsonObject.put("outfits", outfitsJson);

        try (FileWriter fileWriter = new FileWriter(this.file)) {
            fileWriter.write(jsonObject.toString(TAB));  // Pretty print with indentation
        }
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

}

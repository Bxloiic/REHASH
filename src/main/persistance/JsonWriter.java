package main.persistance;

//Imports
import main.model.*;
import java.io.*;
import java.util.*;
import org.json.*;

import main.model.Hash;
import main.model.Hashdex;
import main.model.Outfit;
import main.ui.Menu;

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
    public void write(List<Hash> hashes, List<Hashdex> hashdexes, List<Outfit> outfits) throws IOException {
        JSONObject json = new JSONObject();
        json.put("hashes", hashesToJson(hashes));
        json.put("hashdexes", hashdexesToJson(hashdexes));
        json.put("outfits", outfitsToJson(outfits));

        try (FileWriter file = new FileWriter(this.file)) {
            file.write(json.toString(TAB)); // Write JSON with an indentation of 4 spaces
        }
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // EFFECTS: returns JSON array of outfits
    private JSONArray outfitsToJson(List<Outfit> outfits) {
        JSONArray jsonArray = new JSONArray();
        for (Outfit o : outfits) {
            JSONObject json = new JSONObject();
            json.put("name", o.getName());
            jsonArray.put(json);
        }
        return jsonArray;
    }

    private JSONArray hashesToJson(List<Hash> hashes) {
        JSONArray jsonArray = new JSONArray();
        for (Hash h : hashes) {
            JSONObject json = new JSONObject();
            json.put("name", h.getName());
            jsonArray.put(json);
        }

        return jsonArray;
    }

    private JSONArray hashdexesToJson(List<Hashdex> hashdexs) {
        JSONArray jsonArray = new JSONArray();
        for (Hashdex h : hashdexs) {
            JSONObject json = new JSONObject();
            json.put("name", h.getName());
            jsonArray.put(json);
        }

        return jsonArray;
    }

}

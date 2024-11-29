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
    public void write(List<Hash> hashes, List<Hashdex> hashdexes, List<Outfit> outfits) throws IOException {
        JSONObject json = new JSONObject();
        json.put("hashes", hashesToJson(hashes));
        json.put("hashdexes", hashdexesToJson(hashdexes));
        json.put("outfits", outfitsToJson(outfits));

        try (FileWriter file = new FileWriter(this.file)) {
            file.write(json.toString(TAB));
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
            // converts the hashes within this outfit to JSON
            JSONArray hashesJson = new JSONArray();

            for (Hash h : o.getOutfitHashs()) {
                // objects
                JSONObject hashJson = new JSONObject();
                hashJson.put("type", h.getType());
                hashJson.put("name", h.getName());
                hashJson.put("colour", h.getColour());
                hashJson.put("material", h.getMaterial());
                hashesJson.put(hashJson);
            }
            json.put("hashes", hashesJson);
            jsonArray.put(json);
        }
        return jsonArray;
    }

    // EFFECTS: returns JSON array of hashes
    private JSONArray hashesToJson(List<Hash> hashes) {
        JSONArray jsonArray = new JSONArray();
        for (Hash h : hashes) {
            JSONObject json = new JSONObject();
            json.put("type", h.getType());
            json.put("name", h.getName());
            json.put("colour", h.getColour());
            json.put("material", h.getMaterial());
            jsonArray.put(json);
        }

        return jsonArray;
    }

    // EFFECTS: returns JSON array of hashdex
    private JSONArray hashdexesToJson(List<Hashdex> hashdexs) {
        JSONArray jsonArray = new JSONArray();
        for (Hashdex h : hashdexs) {
            JSONObject json = new JSONObject();
            json.put("name", h.getName());
            json.put("colour", h.getColour());

            JSONArray hashesJson = new JSONArray();
            for (Hash hash : h.getHashList()) {
                // objects
                JSONObject hashJson = new JSONObject();
                hashJson.put("type", hash.getType());
                hashJson.put("name", hash.getName());
                hashJson.put("colour", hash.getColour());
                hashJson.put("material", hash.getMaterial());
                hashesJson.put(hashJson);
            }
            json.put("hashes", hashesJson);
            jsonArray.put(json);

        }

        return jsonArray;
    }

}

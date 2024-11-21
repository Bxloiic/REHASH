package main.persistance;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Imports
import main.model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads data from JSON stored in file 
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads hashes, hashdexes, and outfits from file and returns them as a Map<String, Object>;
    // throws IOException if an error occurs reading data from file
    public Map<String, Object> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseData(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses data from JSON object and returns it as a Map
    private Map<String, Object> parseData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();

       // Parse hashes
        List<Hash> hashes = parseHashes(jsonObject.getJSONArray("hashes"));
        data.put("hashes", hashes);

        // Parse hashdexes
        List<Hashdex> hashdexes = parseHashdexes(jsonObject.getJSONArray("hashdexes"), hashes);
        data.put("hashdexes", hashdexes);

        // Parse outfits
        List<Outfit> outfits = parseOutfits(jsonObject.getJSONArray("outfits"), hashes);
        data.put("outfits", outfits);

        return data;
    }

    // EFFECTS: parses a list of hashes from JSON array and returns it
    private List<Hash> parseHashes(JSONArray jsonArray) {
        List<Hash> hashes = new ArrayList<>();
        for (Object json : jsonArray) { //cycles json objects in the arrayz
            JSONObject nextHash = (JSONObject) json; // get hashdex in JSON format
            String name = nextHash.getString("name"); // get name of hash
            String type = nextHash.getString("type"); // get type of hash
            String color = nextHash.getString("colour"); // get colour of hash
            String material = nextHash.getString("material"); // get material of hash
            Hash hash = new Hash(name, type, color, material); // create new hash object (with same info)
            hashes.add(hash);
        }
        return hashes;
    }

    // EFFECTS: parses a list of hashdexes from JSON array and returns it
    private List<Hashdex> parseHashdexes(JSONArray jsonArray, List<Hash> hashes) {
        List<Hashdex> hashdexes = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextHashdex = (JSONObject) json;
            String name = nextHashdex.getString("name");
            String color = nextHashdex.getString("colour");
            Hashdex hashdex = new Hashdex(name, color);

            // Parse and link hashes within this hashdex
            JSONArray hashArray = nextHashdex.getJSONArray("hashes");
            for (Object hashJson : hashArray) {
                JSONObject hashObject = (JSONObject) hashJson;
                Hash hash = findHashByName(hashes, hashObject.getString("name"));
                if (hash != null) {
                    hashdex.addHash(hash);
                }
            }

            hashdexes.add(hashdex);
        }
        return hashdexes;
    }

    // EFFECTS: parses a list of outfits from JSON array and returns it
    private List<Outfit> parseOutfits(JSONArray jsonArray, List<Hash> hashes) {
        List<Outfit> outfits = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextOutfit = (JSONObject) json;
            String name = nextOutfit.getString("name");
            Outfit outfit = new Outfit(name);

            // Parse and link hashes within this outfit
            JSONArray hashArray = nextOutfit.getJSONArray("hashes");
            for (Object hashJson : hashArray) {
                JSONObject hashObject = (JSONObject) hashJson;
                Hash hash = findHashByName(hashes, hashObject.getString("name"));
                if (hash != null) {
                    outfit.addHash(hash);
                }
            }

            outfits.add(outfit);
        }
        return outfits;
    }

    // EFFECTS: finds a hash by its name in the list of hashes, or returns null if not found
    private Hash findHashByName(List<Hash> hashes, String name) {
        for (Hash hash : hashes) {
            if (hash.getName().equals(name)) {
                return hash;
            }
        }
        return null;
    }

}

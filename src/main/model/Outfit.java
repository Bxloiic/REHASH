package main.model;

// imports
import java.util.*; //imports whole java util library

import org.json.JSONArray;
import org.json.JSONObject;

import main.ui.Event;
import main.ui.EventLog;
import main.persistance.Writable;

// stores specifed hashes to make an outfit
public class Outfit implements Writable {

    // Attributes
    private String name; // Name of the item
    private ArrayList<Hash> outfitHashs; // Tags to describe the style (casual, formal, etc)

    // Constructors
    /*
     * REQUIRES: non-zero length
     * EFFECTS: creates a outfit with a given name
     * initalizes hashs (items) as an empty list
     */
    public Outfit(String name) {
        this.name = name;
        this.outfitHashs = new ArrayList<>();
    
    }

    // ---------GETTERS---------
    public String getName() {
        return name;
    }

    public ArrayList<Hash> getOutfitHashs() {
        return outfitHashs;
    }

    // ---------SETTERS---------
    public void setName(String name) { // sets name of outfit
        this.name = name;
    }

    public void setOutfitHashs(ArrayList<Hash> hashs) { // sets
        this.outfitHashs = hashs;
    }

    // *---------METHODS---------*
    /*
     * MODIFIES: this
     * EFFECTS: adds hashes to outfit hashlist
     */
    public void addHash(Hash hash) {
        outfitHashs.add(hash);
        EventLog.getInstance().logEvent(new Event("Added Hash: " + hash.getName() + " to Outfit: " + this.name));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray hashesJson = new JSONArray();
        for (Hash hash : outfitHashs) {
            hashesJson.put(hash.toJson());
        }
        json.put("hashes", hashesJson);
        return json;
    }

}

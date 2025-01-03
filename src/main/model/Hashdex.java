package main.model;

import java.util.*; //imports whole java util library

import org.json.JSONArray;
import org.json.JSONObject;

import main.ui.Event;
import main.ui.EventLog;
import main.persistance.Writable;

//a virtual closet that can be customized to make a uniquely themed closet
public class Hashdex implements Writable {

    // Attributes
    private String name; // name of hashdex
    private String colour; // colour (hashdex image) of hashdex
    private LinkedList<Hash> hashList; // LinkedList for the hashdex (allows hashes to be add, stored, etc)

    // Constructors
    /*
     * REQUIRES: name and colour to have a non-zero length
     * EFFECTS: creates a closet (hashdex) with the desired features which are set
     * as it's attributes
     * parameter are set to their correlating attribute
     * initializes hashList as an empty linkedlist
     */
    public Hashdex(String name, String colour) {
        this.hashList = new LinkedList<>();
        this.name = name;
        this.colour = colour;
    }

    // ---------SETTERS---------
    public void setName(String name) {
        this.name = name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setLinkedList(LinkedList<Hash> hashList) {
        this.hashList = hashList;
    }

    // ---------GETTERS---------
    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public LinkedList<Hash> getHashList() {
        return hashList;
    }

    public int getHashListSize() {
        return hashList.size();
    }

    // *---------METHODS---------*
    // Organize list by name
    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this
     * EFFECTS: sorts LinkedList in a alphabetical order
     */
    // public void sortByName() {
    // hashList.sort(null);// sorts the list alphabetically
    // // Collections.sort(hashList, Comparator.comparing(Hashdex::getName));
    // }

    /*
     * REQUIRES: a non-empty hash
     * MODIFIES: this, hashList
     * EFFECTS: adds hash to hashdex
     */
    public void addHash(Hash e) { // e stands for
        hashList.add(e);
        EventLog.getInstance().logEvent(new Event("Added Hash: " + e.getName() + " to Hashdex: " + this.name));
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, hashList
     * EFFECTS: removes the last hash from the hashdex
     */
    public void removeHash() {
        EventLog.getInstance()
                .logEvent(new Event(
                        "Removed Hash from Hashdex: " + this.name));
        hashList.removeLast();
    }

    /*
     * REQUIRES: a index greater than or equal 0 and less than hashList.size-1
     * MODIFIES: this, hashList
     * EFFECTS: removes a hash at a specific index from the List
     */
    public void removeHash(int index) {
        EventLog.getInstance()
                .logEvent(new Event("Removed Hash: " + hashList.get(index) + " from Hashdex: " + this.name));
        hashList.remove(index);
    }

    /*
     * MODIFIES: this, hashList
     * EFFECTS: adds hash to hashList if hash is liked
     */
    public void saveHash(Hash hash) {
        if (hash.getLiked()) { // Only add if liked
            hashList.add(hash); // gets hashlist and adds
            EventLog.getInstance().logEvent(new Event("Saved Hash: " + hash.getName() + " to Hashdex: " + this.name));
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("colour", colour);

        JSONArray hashesJson = new JSONArray();
        for (Hash hash : hashList) {
            hashesJson.put(hash.toJson());
        }
        json.put("hashes", hashesJson);
        return json;
    }

}

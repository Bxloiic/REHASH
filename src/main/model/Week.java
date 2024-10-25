package main.model;

// imports
import java.util.*; //imports whole java util library

// Calendar which stores an outfits/day of the week
public class Week {
    // Attributes
    // String is the key, and the outfit is the value that is paired to the key
    private HashMap<String, Outfit> outfits;

    // Constructor
    /*
     * EFFECTS: initializes outfits of the week to an empty HashMap
     */
    public Week() {
        outfits = new HashMap<>();
    }

    // ---------GETTERS---------
    /*
     * EFFECTS: returns the outfits map
     */
    public HashMap<String, Outfit> getOutfits() {
        return outfits;
    }

    // *---------METHODS---------*
    /*
     * REQUIRES: non-zeor length string
     * MODIFIES: this, outfits
     * EFFECTS: adds a outfit to a specified day of the week
     * override days with outfits already with the new inputed outfit
     */
    public void addOutfit(String day, Outfit outfit) {
        outfits.put(day, outfit);
    }

    /*
     * REQUIRES: a non-empty hashmap
     * MODIFIES: this, outfits
     * EFFECTS: removes the day from the hashmap (the key and it's associated value)
     */
    public void removeOutfit(String day) {
        outfits.remove(day); // Remove the day and it's associated outfit
    }


}

package main.model;

// imports
import java.util.*; //imports whole java util library

// stores specific hashes to make an outfit
public class Week {

    // Attributes
    // String is the key, and the outfit is the value that is paired to the key
    private HashMap<String, Outfit> outfits;

    // Constructor
    /*
     * EFFECTS: initializes outfits to an empty HashMap
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
     * MODIFIES: this, outfits
     * EFFECTS: adds a day of the week and it's associated outfit
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

    /*
     * EFFECTS: displays the week with each days corresponding outfit
     */

    public void displayWeek() {
        // iterates through keys of the hashmaps and displays the day with corresponding
        // outfit
        for (String day : outfits.keySet()) {
            Outfit outfit = outfits.get(day);
            System.out.println(day + ": " + outfit.getName());
        }
    }

}

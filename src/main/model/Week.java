package main.model;

// imports
import java.util.*; //imports whole java util library

// stores specific hashes to make an outfit
public class Week {

    // Attributes
    // String is the key, and the outfit is the value that is paired to the key
    private HashMap<String, Outfit> outfits; 

    // Constructors
    /*
     * EFFECTS: initializes outfits to an empty HashMap
     */
    public Week() {
        outfits = new HashMap<>();
    }


    // *---------METHODS---------*
    /*
     * MODIFIES: this, outfits
     * EFFECTS: initializes outfits to an empty HashMap
     */
    public void addOutfit(String day, Outfit outfit){
        outfits.put(day, outfit);
    }

    /*
     * REQUIRES: a non-empty hashmap
     * MODIFIES: this, outfits
     * EFFECTS: initializes outfits to an empty HashMap
     */
    public void removeOutfit(String day) {
        outfits.remove(day); // Remove the outfit associated with the given day
    }

    /*
     * EFFECTS: displays the week with each days corresponding outfit
     */
    public void displayCalendar() {
        // iterates through keys of the hashmaps and displays the day with corresponding outfit
        for (String day : outfits.keySet()) {
            Outfit outfit = outfits.get(day);  
            System.out.println(day + ": " + outfit.getName());
        }
    }


}

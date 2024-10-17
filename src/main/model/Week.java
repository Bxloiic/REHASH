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

    /*
     * EFFECTS: displays the week with each days corresponding outfit
     */

    public void displayWeek() {
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                "Sunday");

        if (outfits.isEmpty()) {
            System.out.println("No outfits have been assigned for the week.");
        } else {
            for (String day : daysOfWeek) {// cycles through daysOfTheWeek
                Outfit outfit = outfits.get(day);// get outfit for that day
                if (outfit != null) { // if day has an assigned outfit...
                    System.out.println(day + ": " + outfit.getName());
                } else {// if day doesn't have an outfit assigned
                    System.out.println(day + ": No outfit assigned.");
                }
            }
        }
    }

}

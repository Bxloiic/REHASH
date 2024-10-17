package main.model;

// imports
import java.util.*; //imports whole java util library

// stores specifed hashes to make an outfit
public class Outfit {

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
    }

    /*
     * EFFECTS: displays the hashs that make the outfitHash list
     */
    @Override
    public String toString() {
        if (outfitHashs.isEmpty()) { // if list is empty
            return "No items have been assigned to outfit!";
        } else {
            return "Outfit: " + name + "\n - Items: " + outfitHashs.toString();
        }
    }

}

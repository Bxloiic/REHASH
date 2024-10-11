package main.model;

// imports
import java.util.*; //imports whole java util library

// stores specific hashes to make an outfit
public class Outfit {

    // Attributes
    private String name; // Name of the item
    private ArrayList<Hash> hashs; // Tags to describe the style (casual, formal, etc)

    // Constructors
    /*
     * EFFECTS: creates a outfit with a given name
     * initaliizes items as an empty arrayList
     */
    public Outfit(String name, Hash testHash) {
        this.name = name;
        this.hashs = new ArrayList<>();
        this.hashs.add(testHash); // adds hash to the neww array
    }

    // ---------GETTERS---------
    public String getName() {
        return name;
    }

    public ArrayList<Hash> getHashs() {
        return hashs;
    }

    // ---------SETTERS---------
    public void setName(String name) { // sets name of outfit
        this.name = name;
    }

    public void setHashs(ArrayList<Hash> hashs) { // sets
        this.hashs = hashs;
    }

    // *---------METHODS---------*
     /*
     * EFFECTS: displays hash name and it's hashes
     */
    @Override
    public String toString() {
        if (hashs.isEmpty()){
            return "No items";
            
        } else{
            return "Outfit: " + name + "\n - Items: " + hashs.toString();
        }
    }

}

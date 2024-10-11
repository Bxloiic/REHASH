package main.model;

// imports
import java.util.*; //imports whole java util library

// stores specific hashes to make an outfit
public class Outfit {

    // Attributes
    private String name; // Name of the item
    private ArrayList<String> hashs; // Tags to describe the style (casual, formal, etc)

    // Constructors
    /*
     * EFFECTS: creates a outfit with a given name
     * initaliizes hashs as an empty arrayList
     */
    public Outfit(String name, List<String> hashs) {
        this.name = name;
        this.hashs = new ArrayList<>();
    }

    // ---------GETTERS---------
    public String getName() {
        return name;
    }

    public ArrayList<String> getHashs() {
        return hashs;
    }

    // ---------SETTERS---------
    public void setName(String name) { // sets name of outfit
        this.name = name;
    }

    public void setHashs(ArrayList<String> hashs) { // sets
        this.hashs = hashs;
    }

    // *---------METHODS---------*
    /*
     * EFFECTS: displays outfit
     */
    public void displayOutfit() {
        for (String e : hashs) {
            System.out.println(e); // iterators over the tags list and prints it's elements
        }
    }

}

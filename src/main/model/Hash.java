package main.model;

// imports
import java.util.*; //imports whole java util library

import org.json.JSONObject;

import main.ui.Event;
import main.ui.EventLog;
import main.persistance.Writable;

//the objects that will be added to closet
public class Hash implements Writable {

    // Attributes
    private String material; // Material of the item (wool, cotton, etc)
    private String colour; // Name of the item
    private String name; // Name of the item
    private String type; // Type of the item (shirt, pants, shoes, etc)
    private List<String> tags; // Tags to describe the style (casual, formal, etc)
    private boolean liked; // Whether the item was liked (saved) by the user

    // Constructors
    /*
     * EFFECTS: creates a hash (clothing item) with given name, colour, material,
     * types.
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    public Hash(String name, String type, String colour, String material) {
        this.name = name;
        this.type = type;
        this.colour = colour;
        this.material = material;
        this.liked = false;
        this.tags = new ArrayList<>();

    }

    // ---------SETTERS---------
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // ---------GETTERS---------
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getColour() {
        return colour;
    }

    public String getMaterial() {
        return material;
    }

    public boolean getLiked() {
        return liked;
    }

    public List<String> getTags() {
        return tags;
    }

    // *---------METHODS---------*
    /*
     * MODIFIES: this
     * EFFECTS: likes hash by changing it's state
     */
    public boolean likedHash() {
        setLiked(true); // sets liked to true
        EventLog.getInstance().logEvent(new Event("Hash liked: " + this.name));
        return this.liked;
    }

    /*
     * MODIFIES: this
     * EFFECTS: unlikes hash by changing it's state
     */
    public boolean unlikedHash() {
        setLiked(false); // set liked to false
        EventLog.getInstance().logEvent(new Event("Hash unliked: " + this.name));
        return this.liked;
    }

    /*
     * EFFECTS: displays the tags within the list
     */
    public void displayTags() {
        for (String e : tags) { // iterators over the tags list and prints it's elements
            System.out.println(e);
        }
        EventLog.getInstance().logEvent(new Event("Displayed tags for hash: " + this.name));
    }

    /*
     * MODIFIES: this
     * EFFECTS: if not already added, add tag to hash
     */
    public void addTag(String tags) {
        if (!getTags().contains(tags)) {
            this.tags.add(tags); // adds a inputed tag to arraylist
        }
        EventLog.getInstance().logEvent(new Event("Tag added to Hash."));
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, tags
     * EFFECTS: removes most recently added tag
     */
    public void removeTag() {
        EventLog.getInstance()
                .logEvent(new Event("Tag : " + this.tags.get(tags.size() - 1) + "removed from hash: " + this.name));
        this.tags.remove(tags.size() - 1); // removes the most recently added element

    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, tags
     * EFFECTS: removes tag by the specified string value
     */
    public void removeTag(String str) {
        this.tags.remove(str); // removed ibject at specified index
    }

    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("colour", colour);
        json.put("material", material);
        return json;
    }

}

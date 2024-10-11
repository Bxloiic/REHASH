package main.model;

// imports
import java.util.*; //imports whole java util library
//the objects that will be added to closet

public class Hash {

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
     * types all
     * set as empty strings
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    public Hash() {
        this.name = "";
        this.type = "";
        this.colour = "";
        this.material = "";
        this.liked = false; // item is not saved in
        this.tags = new ArrayList<>();
    }

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
        setLiked(true);  //sets liked to true
        System.out.println(getName() + " hash has been liked!");
        return this.liked;
    }

    /*
     * MODIFIES: this
     * EFFECTS: unlikes hash by changing it's state
     */
    public boolean unLikedHash(){
        setLiked(false);  //set liked to false
        System.out.println(getName() + " hash has be UNliked!");
        return this.liked;
    }

    /*
     * EFFECTS: displays the tags saved in the arrayList
     */
    public void displayTags(){
        for (String e: tags){
            System.out.println(e);
        }
    }

    public void addTag(String tags){
        this.tags.add(tags);  //adds a inputed tag to arraylist
        System.out.println("Tag has been added SUCCESSFULLY...");
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, tags
     * EFFECTS: removes most recently added tag
     */
    public void removeTag(){
        this.tags.remove(tags.size()-1);  // removes the most recently added element
        System.out.println("Tag has been removed");
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, tags
     * EFFECTS: removes tag at specified index
     */
    public void removeTag(int index){
        this.tags.remove(index);   //removed ibject at specified index
        System.out.println("Tag  at index:"+ index + " has been removed");
    }

}

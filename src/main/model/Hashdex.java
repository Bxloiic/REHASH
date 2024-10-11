package main.model;

import java.util.*;  //imports whole java util library

//a virtual closet that can be customized to make a uniquely themed closet
public class Hashdex {

    // Attributes
    private String name;               // name of hashdex
    private String colour;             // colour (hashdex image) of hashdex
    private LinkedList<Hash> hashList; // LinkedList for the hashdex (allows hashes to be add, stored, etc)

    // Constructors
    /*
     * EFFECTS: creates a blank (default) Hashdex with attributes set to blank
     * (relative to the type)
     * name is set to "Untitled"; colour is set to "white"
     * empty linkedlist is implemented
     */
    public Hashdex() {
        this.name = "Untitled";  // sets name to a default string to signify basic constructor imple
        this.colour = "white";   // se
    }

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

    // *---------METHODS---------*
    // Organize list by name
    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this
     * EFFECTS: sorts LinkedList in a alphabetical order
     */
    public void sortByName() {
        hashList.sort(null);// sorts the list alphabetically
        // Collections.sort(hashList, Comparator.comparing(Hashdex::getName));
    }

    /*
     * REQUIRES: a instance of the hash class
     * MODIFIES: this, hashList
     * EFFECTS: add hash to hashdex
     */
    public void addHash(Hash e) {// e stands for
        hashList.add(e);
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, hashList
     * EFFECTS: removes the last hash from the hashdex
     */
    public void removeHash() {
        hashList.removeLast();
    }

    /*
     * REQUIRES: a index greater than or equal 0 and less than hashList.size-1
     * MODIFIES: this, hashList
     * EFFECTS: removes a hash at a specific index from the List
     */
    public void remove(int index) {
        hashList.remove(index);
    }

    /*
     * REQUIRES: a non-empty list
     * EFFECTS: displays the elements of the linkedlist
     */
    public void displayList() {
        for (Hash h : hashList) {// cycles through every element in the list
            System.out.println(h);// prints the elements in the lsit
        }
    }

    /*
     * MODIFIES: this, hashList
     * EFFECTS: adds hash to hashList if hash is liked
     */
    public void saveHash(Hash hash) {
        if (hash.getLiked()) { // Only add if liked
            hashList.add(hash);  // gets hashlist and adds 
            System.out.println(hash.getName() + " has been added to " + this.name);
        } else {
            System.out.println(hash.getName() + " has not been liked yet.");
        }
    }



    /*
     * EFFECTS: displays hashdex name and it's hashes
     */
    @Override
    public String toString() {
        return "Hashdex: " + name + " - Items: " + hashList.toString();
    }


}

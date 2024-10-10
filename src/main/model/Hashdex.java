package main.model;
import java.util.*;

//a veritual closet that can be customized to make a uniquely themed closet
public class Hashdex {
    // Attributes
    private String name; // name of hashdex
    private String colour; // colour (hashdex image) of hashdex
    private LinkedList <Hash> hashList; // LinkedList for the hashdex (allows hashes to be add, stored, etc)

    // Constructors
    /*
     * 
     * EFFECTS: creates a blank (default) Hashdex with attributes set to blank (relative to the type)
     * name is set to "Untitled"; colour is set to "white"
     * empty linkedlist is implemented
     */
    public Hashdex(){
        this.name = "Untitled";
        this.colour = "white";
    }

    /*
     * REQUIRES: name and colour to have a non-zero length
     * 
     * EFFECTS: creates a closet (hashdex) with the desired features which are set as it's attributes
     * parameter are set to their correlating attribute
     * empty linkedlist is implemented
     */
    public Hashdex(String name, String colour){
        this.hashList = new LinkedList <>();
        this.name = name;
        this.colour = colour;
    }

    //---------SETTERS---------
    public void setName(String name){
        this.name = name;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public void setLinkedList(LinkedList hashList){
        this.hashList = hashList;
    }

    //---------GETTERS---------
    public String getName(){
        return name;
    }

    public String getColour(){
        return colour;
    }

    public LinkedList getHashList(){
        return hashList;
    }

    //---------METHODS---------
    // Organize list by name
    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, hashList
     * EFFECTS: sorts LinkedList in a alphabetical order
     */
    public void sortByName(){
        hashList.sort(null);//sorts the list alphabetically
        //Collections.sort(hashList, Comparator.comparing(Hashdex::getName));
    }

    // public void sortBy(){
    //     Collections.sort(hashList, Comparator.comparing(Hashdex::getName));
    // }

    /*
     * REQUIRES: a instance of the hash class
     * MODIFIES: this, hashList
     * EFFECTS: adds a give hash to the hashList (closet)
     */
    public void addToList(Hash e){// e stands for
        hashList.add(e);
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, hashList
     * EFFECTS: removes the last element in the from the LinkedList
     */
    public void remove(){
        hashList.removeLast();
    }

    /*
     * REQUIRES: a non-empty list
     * MODIFIES: this, hashList
     * EFFECTS: removes a hash at a specific index from the List
     */
    public void remove(Index i){
        hashList.remove(i);
    }

    /*
     * REQUIRES: a non-empty list
     * 
     * EFFECTS: displays the elements of the linkedlist 
     */
    public void displayList(){
        for (Hash h: hashList){//cycles through every element in the list
            System.out.println(h);// prints the elements in the lsit
        }
    }

    
    



}

package main.ui;

// imports
import main.model.*;
import main.persistance.JsonReader;
import main.persistance.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//represents the menu screens options the user can use
public class Menu {

    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    // Attributes
    private Scanner scanner;
    private Week week;
    private Hashdex hashdex;
    private List<Hash> hashes;
    private List<Hashdex> hashdexes;
    private List<Outfit> outfits;
    private boolean valid = true;
    private Map<String, Object> data;
    private int input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private boolean running;
    private DataPrinter dataPrinter;
    private static final String JSON_STORE = "./data/Rehash.json";
    private static final String EMPTY_DATA_JSON = "{ \"hashes\": [], \"hashdexes\": [], \"outfits\": [] }";

    // Constructor
    /*
     * EFFECTS: initializes scanner, week. hashdex, hashes, and hashdexes are
     * initialized to an empty list
     */
    Menu() {
        scanner = new Scanner(System.in); // takes user input from terminal
        week = new Week();
        hashdex = new Hashdex("Default Closet", "Neutral"); // there's a default hashdex until it's edited or removed
        hashes = new ArrayList<>(); // Initialize the list to store created hashes
        hashdexes = new ArrayList<>(); // Initialize the list to store created hashdexs
        hashdexes.add(hashdex);
        outfits = new ArrayList<>();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        dataPrinter = new DataPrinter();

        loadData(); // Load data at the start

    }

    // Constructor
    /*
     * EFFECTS: draws menu options
     */
    public void run() {
        running = true;
        while (running) {
            drawRehashMenu();
        }
    }

    public void drawRehashMenu() {
        try {
            System.out.println(CYAN + "\nPlease Enter an Integer to select one of the following options: " + GREEN);
            System.out.printf("%-40s%-40s%n", "1. Create a Hash (clothing item)", "2. Create a HashDex");
            System.out.printf("%-40s%-40s%n", "3. Create an Outfit", "4. Add Hash to Hashdex (closet)");
            System.out.printf("%-40s%-40s%n", "5. View Hashs (items)", "6. View Hashdexes (closet)");
            System.out.printf("%-40s%-40s%n", "7. View Outfits", "8. View Outfits for the week day");
            System.out.printf("%-40s%-40s%n", "10. Save Current Data", "11. View Loaded Data");
            System.out.printf("%-40s%-40s%n", "12. Clear Current Data", "9. Exit");

            int inputh = scanner.nextInt();
            handleMenuSelection(inputh);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Application exited. Please try again");
            scanner.nextLine();
        }

    }

    /*
     * EFFECTS: handles user input for appilciation menu
     */
    private void handleMenuSelection(int input) {
        switch (input) {
            case 1:
                createHash();
                break;
            case 2:
                createHashdex();
                break;
            case 4:
                addToHashdex();
                break;
            case 3:
                createOutfit();
                break;
            case 8:
                viewWeekOutfit();
                break;
            case 5:
                viewHashs();
                break;
            case 6:
                viewHashdexs();
                break;
            case 7:
                viewOutfits();
                break;
            case 9:
                exitApplication();
                break;
            case 10:
                saveData();
                break;
            case 11:
                viewLoadedData();
                break;
            case 12:
                clearData();
                break;
            default:
                displayInvalidOptionMessage();
                break;
        }
    }

    // EFFECTS: exits program by unrunning menu
    private void exitApplication() {
        valid = false;
        System.out.println(RED + "\nEXITING REHASH...");
        System.exit(0);
    }

    // EFFECTS: displays the rehash menu
    private void displayInvalidOptionMessage() {
        drawRehashMenu(); // Repeat the menu
    }

    //----------------------- JSON DATA METHODS --------------------------
    // EFFECTS: dislays all saved loaded data
    private void viewLoadedData() {
        System.out.println("\n");
        DataPrinter.printLoadedData(data);
    }

    // MODIFIES: this
    // EFFECTS: loads hashes, hashdex, and outfits from file
    private void loadData() {
        try {
            // Load the data into a map
            data = jsonReader.read();
            
            // Populate your data from the map
            hashes = (List<Hash>) data.get("hashes");
            hashdexes = (List<Hashdex>) data.get("hashdexes");
            outfits = (List<Outfit>) data.get("outfits");
        } catch (IOException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    
    }

    // EFFECTS: saves hashes, hashdex, and outfits to file
    public void saveData() {
        try {
            // Create a JsonWriter instance
            JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
            // Save the data (hashes, hashdexes, outfits)
            jsonWriter.open();
            jsonWriter.write(hashes, hashdexes, outfits);
            jsonWriter.close();
            System.out.println("Data successfully saved to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    // EFFECTS: clears current data by writing empty JSON to the file
    public void clearData() {
        // Clear in-memory data
        hashes.clear();
        hashdexes.clear();
        outfits.clear();
        System.out.println("In-memory data cleared.");
    
        try {
            // Overwrite the file with empty data (or clear the file entirely)
            JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            jsonWriter.close();
            System.out.println("Data file cleared.");
        } catch (IOException e) {
            System.out.println("Failed to clear data file: " + e.getMessage());
        }
    }


    //----------------------- HASH METHODS --------------------------
    /*
     * EFFECTS: creates a hash (clothing item) with given name, colour, material,
     * types from user
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    private void createHash() {
        scanner.nextLine(); // consums extra inputs
        System.out.println(RED + "\nCreating new hash...");

        String name = getInput("What is the name of the item?: ");
        String type = getInput("What is the type of " + name + ": ");
        String colour = getInput("What is the colour of " + name + ": ");
        String material = getInput("What is the material of " + name + ": ");

        Hash hash = new Hash(name, type, colour, material);
        hash.likedHash();
        hashes.add(hash);

        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made a hash!");
        hashFollowUpAction();
    }

    // EFFECTS: gets user input
    private String getInput(String prompt) {
        System.out.println(GREEN + prompt);
        return scanner.nextLine();
    }

    // EFFECTS: displays potential options of choice after hash is created
    private void hashFollowUpAction() {
        System.out.println(GREEN + "To see your hash, press [5]...");
        System.out.println("To add hash to hashdex, press [4]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine();
        handleMenuSelection(input);
    }

    //----------------------- HASHDEX METHODS --------------------------

    /*
     * REQUIRES: name and colour to have a non-zero length
     * EFFECTS: creates a closet (hashdex) with the desired features which are set
     * as it's attributes
     * parameter are set to their correlating attribute
     */
    private void createHashdex() {
        System.out.println(RED + "\nCreating new hashdex...");

        System.out.println(GREEN + "To create a new Hashdex, please answer the questions below: ");
        System.out.println("Name your hashdex: ");
        scanner.nextLine();
        String name = scanner.nextLine();// saves user input as a name

        System.out.println("What is the theme (colour) of the " + name + ": ");
        String colour = scanner.nextLine(); // saves user input as colour
        Hashdex hash = new Hashdex(name, colour); // create hash object

        hashdexes.add(hash); // Store the created hashdex
        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made a Hashdex!");
        System.out.println(GREEN + "To see your hashdex, press [6]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        handleMenuSelection(input);
    }

    /*
     * MODIFIES: this, hashdex
     * EFFECTS: adds a created hash to a hashdex if not already there
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    private void addToHashdex() {
        scanner.nextLine(); // consumes extra sinputs
        String hashName = getInput("Enter Hash Name: ");
        Hash verifiedHash = findHashByName(hashName);

        if (verifiedHash == null) {
            System.out.println("\nHash not found.");
            return;
        }

        String hashdexName = getInput("Enter Hashdex Name: ");
        Hashdex verHashdex = findHashdexByName(hashdexName);

        if (verHashdex == null) {
            System.out.println("\nHashdex not found.");
            return;
        }

        verHashdex.addHash(verifiedHash);
        System.out.println(YELLOW + "\nHash has been added to your Hashdex!!!");
        hashFollowUpAction();
    }

    /**
     * EFFECTS: Searches for a hash by name. Returns null if not found.
     */
    private Hash findHashByName(String hashName) {
        for (Hash h : hashes) {
            if (h.getName().equalsIgnoreCase(hashName)) {
                return h;
            }
        }
        return null;
    }

    /**
     * EFFECTS: Searches for a hashdex by name. Returns null if not found.
     */
    private Hashdex findHashdexByName(String hashdexName) {
        for (Hashdex hd : hashdexes) {
            if (hd.getName().equalsIgnoreCase(hashdexName)) {
                return hd;
            }
        }
        return null;
    }

    //----------------------- OUTFIT METHODS --------------------------
    /*
     * REQUIRES: non-zero length
     * EFFECTS: creates a outfit with a given name
     * initaliizes items as an empty arrayList
     */
    private void createOutfit() {
        scanner.nextLine(); // consumes any excess inputs

        System.out.println(RED + "\ncreating an outfit...");
        if (hashes.isEmpty()) {
            System.out.println("Can't make outfit! No hashes have been made yet!" + GREEN);
            return;
        }

        System.out.println("Assign an Outfit Name: ");
        String outfitName = scanner.nextLine();

        Outfit of = new Outfit(outfitName);

        // Let the user choose a hash to add to the outfit
        System.out.println("Select a hash (enter hash name):");
        String name = scanner.nextLine();

        for (Hash h : hashes) { // cycles through created hashes
            if (h.getName().equals(name)) {
                if (!of.getOutfitHashs().contains(h)) {
                    of.addHash(h);
                }
            }
        }

        outfits.add(of); // adds outfit to created outfit list
        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made an Outfit!");
        outfitFollowUp();
    }

    private void outfitFollowUp() {
        System.out.println(GREEN + "To add outfit to day of the week, press [5]...");
        System.out.println(GREEN + "To see your Outfit, press [7]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        handleMenuSelection(input);
    }

    /*
     * EFFECTS: displays the created outfits
     */
    private void viewOutfits() {
        System.out.println(RED + "\nViewing your Outfits...:" + GREEN);
        if (outfits.isEmpty()) {
            System.out.println("No outfits have been created yet!");
            return;
        }

        for (Outfit outfit : outfits) {
            // Display the outfit's name and its included hashes
            System.out.println("Outfit Name: " + outfit.getName());
            System.out.println("Included items in this outfit:");

            for (Hash hash : outfit.getOutfitHashs()) {
                System.out.println("  - " + hash.getName() + " (" + hash.getType() + ", " + hash.getColour() + ")");
            }

            System.out.println();
        }
        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // ensures it doesn't read future inputs wrong
        handleMenuSelection(input);
    }

    /*
     * MODIFIES: this, week
     * EFFECTS: adds outfit to a days of the week
     */
    private void addOutfitToDay(Outfit outfit) {
        if (outfits.isEmpty()) { // checks if there are any outfits in the outfit list
            System.out.println(RED + "No outfits available to view...");
            return;
        } else { // make an oufit for a specific day
            System.out.println(GREEN + "Which outfit would you like to assign to a day?");
            System.out.println("Enter a day of the week (e.g., Monday): ");
            String day = scanner.nextLine();
            // adds outfit to the day of the week (if another outfit is added to a day with
            // an outfit)
            // the old outfit is override and replaced with the newly added outfit
            week.getOutfits().put(day, outfit); // Assign the outfit to the specified day
            System.out.println(YELLOW + "\nOutfit assigned to " + day + "!");
            System.out.println("To view this weeks outfits, press [8]...");
            System.out.println("To return to main menu, press [0]...");
            handleMenuSelection(input);
        }
    }

    /*
     * EFFECTS: displays the week with each days corresponding outfit
     */
    private void viewWeekOutfit() {
        System.out.println(RED + "\nViewing outfits for the week...:" + GREEN);

        // Display outfits assigned to the week (use displayWeek() method in Week class)
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
                "Sunday");

        if (outfits.isEmpty()) {
            System.out.println("No outfits have been assigned for the week.");
        } else {
            for (String day : daysOfWeek) { // cycles through daysOfTheWeek
                Outfit outfit = week.getOutfits().get(day);// get outfit for that day
                if (outfit != null) { // if day has an assigned outfit...
                    System.out.println(day + ": " + outfit.getName());
                } else { // if day doesn't have an outfit assigned
                    System.out.println(day + ": No outfit assigned.");
                }
            }
        }
        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        handleMenuSelection(input);
    }

    /*
     * EFFECTS: displays hashdex name and it's hashes
     */
    private void viewHashdexs() {
        System.out.println(RED + "\nViewing your Hashdexes (closets):" + GREEN);
        for (Hashdex h : hashdexes) {
            // Display items in the hashdex (use displayList() method in Hashdex class)
            System.out.println("Hashdex Name: " + h.getName());
            if (h.getHashList().isEmpty()) {
                System.out.println("This hashdex is empty.");
            } else {
                System.out.println("Items in this hashdex:");
                for (Hash hash : h.getHashList()) {
                    System.out.println(hash.getName() + " (" + hash.getType() + ", " + hash.getColour() + ")");
                }
            }
            System.out.println();

        }
        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        handleMenuSelection(input);
    }

    /*
     * EFFECTS: displays made hashes
     */
    private void viewHashs() {
        System.out.println(RED + "\nViewing your Hashs...:" + GREEN);
        if (hashes.isEmpty()) {
            System.out.println("No hashes have been made yet!");
            return;

        }
        for (Hash h : hashes) {
            // Display items in the hashdex (use displayList() method in Hashdex class)
            System.out.println("Hash Name: " + h.getName());
            System.out.println("Details of this hash: ");
            System.out.println(h.getName() + ":  Type: " + h.getType() + ", Colour: " + h.getColour() + ", Material: "
                    + h.getMaterial() + ", Liked:" + h.getLiked());

            System.out.println();

        }

        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        handleMenuSelection(input);
    }

}

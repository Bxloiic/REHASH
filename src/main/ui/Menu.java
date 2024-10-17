package main.ui;

// imports
import main.model.*;
import java.util.*; //  imports entire java.util library

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
    private int input;

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

    }

    // Constructor
    /*
     * EFFECTS: draws menu options
     */
    public void run() {
        drawMenu();
    }

    public void drawMenu() {
        while (valid) {
            System.out.println(CYAN + "Please Enter an Integer to select one of the following options: " + GREEN);
            System.out.println("1. Create a new Hash (clothing item)");
            System.out.println("2. Create a HashDex");
            System.out.println("3. Add Hash to Hashdex (closet)");
            System.out.println("4. Create an Outfit");
            System.out.println("5. View Outfits for the week day");
            System.out.println("6. View Hashs (items)");
            System.out.println("7. View Hashdexes (closet)");
            System.out.println("8. Exit");

            // Exception handling
            try {
                input = scanner.nextInt(); // only takes in valid integer
                switch (input) {
                    case 1:
                        createHash();
                        break;
                    case 2:
                        createHashdex();
                        break;
                    case 3:
                        addToHashdex();
                        break;
                    case 4:
                        createOutfit();
                        break;
                    case 5:
                        viewWeekOutfit();
                        break;
                    case 6:
                        viewHashs();
                        break;
                    case 7:
                        viewHashdexs();
                        break;
                    case 8:
                        valid = false;
                        System.out.println(RED + "\nEXITING REHASH...");
                        System.exit(0);
                    default:
                        System.out.println(RED + "Invalid input! Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Application exited. Please try again");
                scanner.nextLine();
            }
        }

    }

    /*
     * EFFECTS: creates a hash (clothing item) with given name, colour, material,
     * types from user
     * initaliizes tags as an empty arrayList and sets liked to false
     */

    private void createHash() {
        System.out.println(RED + "\nCreating new hash...");

        scanner.nextLine();
        System.out.println(GREEN + "To create a new hash, please answer the questions below: ");
        System.out.println("What is the name of the item?: ");
        String name = scanner.nextLine();// saves user input as a name

        System.out.println("What is the type of " + name + ": ");
        String type = scanner.nextLine(); // saves user input as type
        System.out.println("What is the colour of " + name + ": ");
        String colour = scanner.nextLine(); // saves user input as colour
        System.out.println("What is the material of " + name + ": ");
        String material = scanner.nextLine(); // saves user input as material

        Hash hash = new Hash(name, type, colour, material); // create hash object
        hash.likedHash(); // like the hash
        hashes.add(hash); // Add this line to store the created hash
        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made a hash!");
        System.out.println(GREEN + "To see your hash, press [1]...");
        System.out.println("To add hash to hashdex, press [2]...");
        // System.out.println("To add hash to outfit, press [3]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 1:
                System.out.println(hash);
                System.out.println("\n");
                break;
            case 2:
                addToHashdex(); // Add the created hash directly to a Hashdex
                break;
            case 0:
                drawMenu();
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }
    }

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
        System.out.println(GREEN + "To see your hashdex, press [1]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 1:
                viewHashdexs();
                break;
            case 0:
                drawMenu();
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }
    }

    /*
     * MODIFIES: this, hashdex
     * EFFECTS: adds a created hash to a hashdex if not already there
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    private void addToHashdex() {
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        System.out.println(RED + "\nadding hash to hashdex..." + GREEN);
        System.out.println("Enter Hash Name: ");
        String hashName = scanner.nextLine(); // stores name of hash

        Hash verifiedHash = null;
        for (Hash h : hashes) { // cycles through list of created hashes;
            if (h.getName().equalsIgnoreCase(hashName)) { // stores hash in verified Hash
                verifiedHash = h;
                break;
            }
        }

        if (verifiedHash == null) { // Check if hash is found
            System.out.println("\nHash not found."); // if not
            return; // Exit method if the hash was not found
        }

        System.out.println("Enter Hashdex Name: ");
        String hashdexName = scanner.nextLine(); // stores hashdex name

        Hashdex verHashdex = null;
        for (Hashdex hd : hashdexes) { // cycles through list of created hashdexes
            if (hd.getName().equalsIgnoreCase(hashdexName)) {
                verHashdex = hd; // stores hash in verified Hashdex
                System.out.println(YELLOW + "\nHash has been added to your Hashdex!!!");
                break;
            }
        }

        if (verHashdex == null) { // Check if hash is found
            System.out.println("\nHashdex not found."); // if not
            return; // Exit method if the hash was not found
        }

        verHashdex.addHash(verifiedHash);
        System.out.println(GREEN + "\nTo see your hashdex, press [1]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 1:
                viewHashdexs();
                break;
            case 0:
                drawMenu();
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }
    }

    /*
     * REQUIRES: non-zero length
     * EFFECTS: creates a outfit with a given name
     * initaliizes items as an empty arrayList
     */
    private void createOutfit() {
        scanner.nextLine();
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

        for (Hash h : hashes) {// cycles through created hashes
            if (h.getName().equals(name)) {
                if (!of.getOutfitHashs().contains(h)) {
                    of.addHash(h);
                }
            }
        }
        outfits.add(of); // adds outfit to created outfit list
        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made an Outfit!");
        System.out.println(GREEN + "To see your Outfit, press [1]...");
        System.out.println(GREEN + "To add outfit to day of the week, press [2]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 2:
                addOutfitToDay(of);
                break;
            case 1:
                viewWeekOutfit();
                break;
            case 0:
                drawMenu();
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }

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

            System.out.println("To view this weeks outfits, press [1]...");
            System.out.println("To return to main menu, press [0]...");
            switch (input) {
                case 1:
                    viewWeekOutfit();
                    break;
                case 0:
                    drawMenu();
                    break;
                default:
                    System.out.println("Invalid input! Returning to main menu.");
                    drawMenu();
                    break;
            }

        }
    }

    /*
     * EFFECTS: displays the week with each days corresponding outfit
     */
    private void viewWeekOutfit() {
        System.out.println(RED + "\nViewing outfits for the week...:" + GREEN);

        week.displayWeek(); // Display outfits assigned to the week (use displayWeek() method in Week class)
        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 0:
                drawMenu();
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }
    }

    /*
     * EFFECTS: displays hashdex name and it's hashes
     */
    private void viewHashdexs() {
        System.out.println(RED + "\nViewing your Hashdexes (closets):" + GREEN);
        for (Hashdex h : hashdexes) {
            h.displayList(); // Display items in the hashdex (use displayList() method in Hashdex class)
            System.out.println();

        }

        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 0:
                drawMenu();
                System.out.println("\n");
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }

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
            h.displayList(); // Display items in the hashdex (use displayList() method in Hashdex class)
            System.out.println();

        }

        System.out.println("\nTo return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 0:
                drawMenu();
                System.out.println("\n");
                break;
            default:
                System.out.println("Invalid input! Returning to main menu.");
                drawMenu();
                break;
        }

    }

}

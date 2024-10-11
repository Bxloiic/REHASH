package main.ui;

// imports
import main.model.*;
import java.util.*; //  imports entire java.util library

//represents the menu screens the user can choose from
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
    private boolean valid = true;
    private int input;
    private String uinput;

    // Constructor
    /*
     * EFFECTS: initializes scanner, week, and hashdex
     */
    Menu() {
        scanner = new Scanner(System.in); // takes user input from terminal
        week = new Week();
        hashdex = new Hashdex("Default Closet", "Neutral"); // there's a default hashdex until it's edited or removed
    }

    public void drawMenu() {
        while (valid) {
            System.out.println(CYAN + "Please Enter an Integer to select one of the following options: " + GREEN);
            System.out.println("1. Create a new Hash (clothing item)");
            System.out.println("2. Create a HashDex");
            System.out.println("3. Add Hash to Hashdex (closet)");
            System.out.println("4. Create an Outfit");
            System.out.println("5. Add outfit to week day");
            System.out.println("6. View Hashdexs (closet)");
            System.out.println("7. Exit");

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
                        viewHashdexs();
                        break;
                    case 7:
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
        System.out.println("What is the name of the object?: ");
        String name = scanner.nextLine();// saves user input as a name

        System.out.println("What is the type of the object?: ");
        String type = scanner.nextLine(); // saves user input as type
        System.out.println("What is the colour of the object?: ");
        String colour = scanner.nextLine(); // saves user input as colour
        System.out.println("What is the material of the object?: ");
        String material = scanner.nextLine(); // saves user input as material

        Hash hash = new Hash(name, type, colour, material); // create hash object
        System.out.println(YELLOW + "\nYou have SUCCESSFULLY made a hash!");
        System.out.println(GREEN + "To see your hash, press [1]...");
        System.out.println("To add hash to hashdex, press [2]...");
        System.out.println("To add hash to outfit, press [3]...");
        System.out.println("To return to main menu, press [0]...");
        input = scanner.nextInt();
        scanner.nextLine(); // makes sure it doesn't read future inputs wrong
        switch (input) {
            case 1:
                System.out.println(hash);
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
        System.out.println("What is the name?: ");
        scanner.nextLine();
        String name = scanner.nextLine();// saves user input as a name

        System.out.println("What is the theme (colour) of the Hashdex?: ");
        String colour = scanner.nextLine(); // saves user input as colour
        Hashdex hash = new Hashdex(name, colour); // create hash object
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
     * REQUIRES: non-zero length
     * EFFECTS: creates a outfit with a given name
     * initaliizes items as an empty arrayList
     */
    private void createOutfit() {
        if (hashdex.getHashList().isEmpty()) { // checks if there are any hashes from hashdex to make an outfit
            System.out.println(RED + "No hashes available to create an outfit.");
            return;
        }

        System.out.println(RED + "\ncreating an outfit..." + GREEN);
        System.out.println("Enter Outfit Name: ");
        String name = scanner.nextLine();

        // Let the user choose a hash to add to the outfit
        System.out.println("Select a hash from your Hashdex:");
        for (int i = 0; i < hashdex.getHashList().size(); i++) { // c ycles through te hashes in the hashdex
            System.out.println(i + ": " + hashdex.getHashList().get(i).getName()); // gets the name of the has at a
                                                                                   // specific index
        }

        int hashIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (hashIndex >= 0 && hashIndex < hashdex.getHashList().size()) {
            Hash selectedHash = hashdex.getHashList().get(hashIndex);
            Outfit outfit = new Outfit(name, selectedHash);

            System.out.println(YELLOW + "\nYou have SUCCESSFULLY made an Outfit!");
            System.out.println(GREEN + "To see your Outfit, press [1]...");
            System.out.println("To return to main menu, press [0]...");
            input = scanner.nextInt();
            scanner.nextLine(); // makes sure it doesn't read future inputs wrong
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
     * MODIFIES: this, hashdex
     * EFFECTS: adds a created hash to a hashdex in the hashdex
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    private void addToHashdex() {
        System.out.println(RED + "\nadding hash to hashdex..." + GREEN);
        System.out.println("Enter Hashdex Name: ");
        String name = scanner.next();
        for (Hash h : hashdex.getHashList()) {
            if (h.getName().equals(name)) {
                hashdex.addHash(h);
                System.out.println(YELLOW + "\nHash has been added to your Hashdex!!!");
                return;
            }
        }
        System.out.println("Hash not found.");
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
     * MODIFIES: this, week
     * EFFECTS: adds outfit to a day of the week
     */
    private void outfitToDay() {
        if (week.getOutfits().isEmpty()) { // checks if outfits have been asisgned or maid yet, if not
            System.out.println(RED + "No outfits available to assign to a day.");
            return;
        } else { // make an oufit for a specific day
            System.out.println(GREEN + "Which outfit would you like to assign to a day?");
            for (String day : week.getOutfits().keySet()) { // iterates through the keys (days of the week) in the
                                                            // hashmap and returns the setkey of available days
                System.out.println(day + ": " + week.getOutfits().get(day).getName());
            }

            System.out.println("Enter a day of the week (e.g., Monday): ");
            String day = scanner.nextLine();
            week.getOutfits().containsKey(day);

            System.out.println(YELLOW + "\nYAAYY!! Outfit has been assigned to +" + day);
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
        System.out.println(RED + "\nViewing outfits for the week:" + GREEN);
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
        hashdex.displayList(); // Display items in the hashdex (use displayList() method in Hashdex class)
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

}

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
    private boolean running = true;
    private int input;

    // Constructor
    /*
     * EFFECTS: initializes scanner, week, and hashdex
     */
    public Menu() {
        scanner = new Scanner(System.in); // takes user input from terminal
        week = new Week();
        hashdex = new Hashdex("Default Hashdex", "White"); // there's a default hashdex until it's edited or removed
    }

    public void displayMenu() {
        while (running) {
            System.out.println(CYAN + "Please Enter an Integer to select one of the following options: " + GREEN);
            System.out.println("1. Create a new Hash (clothing item)");
            System.out.println("2. Add Hash to Hashdex (closet)");
            System.out.println("3. Create an Outfit");
            System.out.println("4. Add outfit to week day");
            System.out.println("6. Exit");

            // Exception handling
            try {
                input = scanner.nextInt(); // only takes in valid integer

                switch (input) {
                    case 1: addHash(); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Application exited. Please try again");
            }
        }
    }

    public void addHash(){
        System.out.println(RED+"\nCreating new hash...");
    }

}

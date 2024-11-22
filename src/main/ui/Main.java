package main.ui;

import main.ui.gui.MainScreen;
import main.ui.gui.RehashScreen;

//runs the application
public class Main {
    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Welcome message
        System.out.println(GREEN + "\nWelcome user to REHASH!");
        System.out.println("REHASH is designed for anyone who seeks inspiration for outfits,");
        System.out.print("whether it’s for daily wear, or wardrobe planning for the week.");
        System.out.println(" \nIt’s also for those who want to build collections");
        System.out.print("as share their personal styles to others who can appreciate their creation.");
        System.out.println("\n------------------------------------------------------------------------");

        // Launch the GUI
        // Create an instance of MainScreen (the GUI class)
        RehashScreen rehashScreen = new RehashScreen();
        //mainScreen.setVisible(true);  // Show the window


    }

}

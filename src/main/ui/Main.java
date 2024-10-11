package main.ui;

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
        System.out.println(
                "REHASH is designed for anyone who seeks inspiration for outfits, whether it’s for daily wear, or wardrobe planning for the week.");
        System.out.print(
                "It’s also for those who want to build collections as share their personal styles to others who can appreciate their creation.");
        System.out.println("\n--------------------------------\n");
       
        //Calls user ui class
        Menu m = new Menu();
        m.displayMenu();

    }

}

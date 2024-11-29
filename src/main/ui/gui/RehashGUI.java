package main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.model.*;
import main.persistance.JsonReader;
import main.persistance.JsonWriter;
import main.persistance.Writable;
import main.ui.EventLog;
import main.ui.Event;

import java.util.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class RehashGUI implements WindowListener {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea; // for displaying info
    private HashMap<String, Hash> hashes;
    private HashMap<String, Hashdex> hashdexes;
    private HashMap<String, Outfit> outfits; // store outfits by name
    private Week week;

    // -------- button fields --------
    private JButton createHashButton;
    private JButton createHashdexButton;
    private JButton viewHashesButton;
    private JButton viewHashdexesButton;
    private JButton createOutfitButton;
    private JButton addHashToOutfitButton;
    private JButton addHashToHashdexButton;
    private JButton viewOutfitsButton;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private JButton clearDataButton;
    private JButton exitButton;

    public RehashGUI() {
        // initialize collections to store data
        hashes = new HashMap<>();
        hashdexes = new HashMap<>();
        outfits = new HashMap<>();
        week = new Week();

        frame = new JFrame("REHASH");
        panel = new JPanel();
        panel.setBackground(Color.decode("#edeec9"));

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false); // user can't edit textarea
        textArea.setBackground(Color.decode("#dde7c7"));
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#bfd8bd"), 2));
        textArea.setFont(new Font("Serif", Font.PLAIN, 15));
        panel.add(new JScrollPane(textArea)); // add text area with scrollability

        createButtons();
        buttonListeners();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(this);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private void createButtons() {
        // Buttons to interact with the program
        createHashButton = new JButton("Create a Hash");
        createHashdexButton = new JButton("Create a Hashdex");
        viewHashesButton = new JButton("View Hashes");
        viewHashdexesButton = new JButton("View Hashdexes");
        createOutfitButton = new JButton("Create an Outfit");
        addHashToOutfitButton = new JButton("Add Hash to Outfit");
        addHashToHashdexButton = new JButton("Add Hash to Hashdex");
        viewOutfitsButton = new JButton("View Outfits");
        saveDataButton = new JButton("Save Data");
        loadDataButton = new JButton("Load Data");
        clearDataButton = new JButton("Clear Data");
        exitButton = new JButton("Exit");

        // Add buttons to panel
        addToFrame();
    }

    private void addToFrame() {
        // Add buttons to panel
        panel.add(createHashButton);
        panel.add(createHashdexButton);
        panel.add(viewHashesButton);
        panel.add(viewHashdexesButton);
        panel.add(createOutfitButton);
        panel.add(addHashToOutfitButton);
        panel.add(addHashToHashdexButton);
        panel.add(viewOutfitsButton);
        panel.add(saveDataButton);
        panel.add(loadDataButton);
        panel.add(clearDataButton);
        panel.add(exitButton);
    }

    private void buttonListeners() {
        // action listeners for each button
        createHashButton.addActionListener(e -> createHash());
        createHashdexButton.addActionListener(e -> createHashdex());
        viewHashesButton.addActionListener(e -> viewHashes());
        viewHashdexesButton.addActionListener(e -> viewHashdexes());
        createOutfitButton.addActionListener(e -> createOutfit());
        addHashToOutfitButton.addActionListener(e -> addHashToOutfit());
        addHashToHashdexButton.addActionListener(e -> addHashToHashdex());
        viewOutfitsButton.addActionListener(e -> viewOutfits());
        saveDataButton.addActionListener(e -> saveData());
        loadDataButton.addActionListener(e -> loadData());
        clearDataButton.addActionListener(e -> clearData());
        exitButton.addActionListener(e -> exitApplication());
    }

    // ----------------------- HASH METHODS --------------------------
    /*
     * EFFECTS: creates a hash (clothing item) with given name, colour, material,
     * types from user
     */
    private void createHash() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the item:");
        String type = JOptionPane.showInputDialog(frame, "Enter the type of the " + name + ":");
        String colour = JOptionPane.showInputDialog(frame, "Enter the colour of " + name + ":");
        String material = JOptionPane.showInputDialog(frame, "Enter the material of " + name + ":");

        Hash hash = new Hash(name, type, colour, material);
        hashes.put(name, hash);
        textArea.append("Created Hash: " + hash.getName() + "\n");
    }

    /*
     * EFFECTS: displays made hashes
     */
    private void viewHashes() {
        if (hashes.isEmpty()) {
            textArea.append("No Hashes available.\n");
            return;
        }

        textArea.append("===== Displaying All Hashes =====\n");
        for (Hash hash : hashes.values()) {
            textArea.append("Hash Name: " + hash.getName() + "\n");
            textArea.append("  Type: " + hash.getType() + "\n");
            textArea.append("  Colour: " + hash.getColour() + "\n");
            textArea.append("  Material: " + hash.getMaterial() + "\n");
            textArea.append("-----------------------------------\n");
        }
    }

    // ----------------------- HASHDEX METHODS --------------------------
    /*
     * REQUIRES: name and colour to have a non-zero length
     * EFFECTS: creates a closet (hashdex) with the desired features which are set
     * as it's attributes
     * parameter are set to their correlating attribute
     */
    private void createHashdex() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the Hashdex:");
        String colour = JOptionPane.showInputDialog(frame, "Enter the colour of the Hashdex:");

        Hashdex hashdex = new Hashdex(name, colour);
        hashdexes.put(name, hashdex);
        textArea.append("Created Hashdex: " + hashdex.getName() + "\n");
    }

    /*
     * EFFECTS: displays hashdex name and it's hashes
     */
    private void viewHashdexes() {
        if (hashdexes.isEmpty()) {
            textArea.append("No Hashdexes available.\n");
            return;
        }

        textArea.append("===== Displaying All Hashdexes =====\n");
        for (Hashdex hashdex : hashdexes.values()) {
            textArea.append("Hashdex Name: " + hashdex.getName() + "\n");
            textArea.append("  Colour: " + hashdex.getColour() + "\n");
            textArea.append("  Contained Hashes:\n");

            if (hashdex.getHashList().isEmpty()) {
                textArea.append("    (No hashes added to this hashdex)\n");
            } else {
                for (Hash hash : hashdex.getHashList()) {
                    textArea.append("    - " + hash.getName() + " (" + hash.getType() + ", "
                            + hash.getColour() + ")\n");
                }
            }

            textArea.append("-----------------------------------\n");
        }
    }

    /*
     * MODIFIES: this, hashdex
     * EFFECTS: adds a created hash to a hashdex if not already there
     * initaliizes tags as an empty arrayList and sets liked to false
     */
    private void addHashToHashdex() {
        String hashName = JOptionPane.showInputDialog(frame, "Enter the name of the Hash:");
        String hashdexName = JOptionPane.showInputDialog(frame, "Enter the name of the Hashdex:");

        Hash hash = hashes.get(hashName);
        Hashdex hashdex = hashdexes.get(hashdexName);
        if (hash != null && hashdex != null) {
            hashdex.addHash(hash);
            textArea.append("Add Hash: " + hash.getName() + " to Hashdex: " + hashdex.getName() + "\n");
        } else {
            textArea.append("Invalid Hash or Hashdex name\n");
        }
    }

    // ----------------------- OUTFIT METHODS --------------------------
    /*
     * REQUIRES: non-zero length
     * EFFECTS: creates a outfit with a given name
     */
    private void createOutfit() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the outfit:");
        Outfit outfit = new Outfit(name);
        outfits.put(name, outfit);
        textArea.append("Created Outfit: " + outfit.getName() + "\n");
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a created hash to a outfit if not already there
     */
    private void addHashToOutfit() {
        String hashName = JOptionPane.showInputDialog(frame, "Enter the name of the hash:");
        String outfitName = JOptionPane.showInputDialog(frame, "Enter the name of the outfit to add to:");

        Outfit outfit = outfits.get(outfitName);
        Hash hash = hashes.get(hashName);

        if (outfit != null && hash != null) {
            outfit.addHash(hash);
            textArea.append("Add Hash: " + hash.getName() + " to Outfit: " + outfit.getName() + "\n");
        } else {
            textArea.append("Invalid Outfit or Hash name\n");
        }
    }

    /*
     * EFFECTS: displays the created outfits
     */
    private void viewOutfits() {
        if (outfits.isEmpty()) {
            textArea.append("No Outfits available.\n");
            return;
        }

        textArea.append("===== Displaying All Outfits =====\n");
        for (Outfit outfit : outfits.values()) {
            textArea.append("Outfit Name: " + outfit.getName() + "\n");
            textArea.append("  Contained Hashes:\n");

            if (outfit.getOutfitHashs().isEmpty()) {
                textArea.append("    (No hashes added to this outfit)\n");
            } else {
                for (Hash hash : outfit.getOutfitHashs()) {
                    textArea.append("    - " + hash.getName() + " (" + hash.getType() + ", "
                            + hash.getColour() + ")\n");
                }
            }

            textArea.append("-----------------------------------\n");
        }
    }

    // ----------------------- JSON DATA METHODS --------------------------
    // EFFECTS: saves hashes, hashdex, and outfits to file
    private void saveData() {

        List<Writable> writables = new ArrayList<>();
        writables.addAll(hashes.values()); // Add all Hash objects
        writables.addAll(hashdexes.values()); // Add all Hashdex objects
        writables.addAll(outfits.values()); // Add all Outfit objects
        JsonWriter jsonWriter = new JsonWriter("./data/Rehash.json");
        try {
            jsonWriter.open();
            jsonWriter.write(writables);
            jsonWriter.close();
            textArea.append("Data saved successfully.\n");

        } catch (Exception e) {
            textArea.append("Error saving data: " + e.getMessage() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hashes, hashdex, and outfits from file
    private void loadData() {
        try {
            JsonReader jsonReader = new JsonReader("./data/Rehash.json");
            Map<String, Object> data = jsonReader.read();

            List<Hash> loadedHashes = (List<Hash>) data.get("hashes");
            List<Hashdex> loadedHashdexes = (List<Hashdex>) data.get("hashdexes");
            List<Outfit> loadedOutfits = (List<Outfit>) data.get("outfits");

            // Delegate the loading tasks
            for (Hash hash : loadedHashes) {
                hashes.put(hash.getName(), hash);
            }
            for (Hashdex hashdex : loadedHashdexes) {
                hashdexes.put(hashdex.getName(), hashdex);
            }
            for (Outfit outfit : loadedOutfits) {
                outfits.put(outfit.getName(), outfit);
            }
            textArea.append("Data loaded successfully.\n");

        } catch (Exception e) {
            textArea.append("Error loading data: " + e.getMessage() + "\n");
        }
    }

    // EFFECTS: clears current data by writing empty JSON to the file
    private void clearData() {
        hashes.clear();
        hashdexes.clear();
        outfits.clear();
        week = new Week();
        textArea.append("All data has been cleared.\n");
        try {
            // overrides the file with empty data
            JsonWriter jsonWriter = new JsonWriter("./data/Rehash.json");
            jsonWriter.open();

            List<Writable> emptyWritableList = new ArrayList<>();

            jsonWriter.write(emptyWritableList);
            jsonWriter.close();
            System.out.println("Data file cleared.");
        } catch (IOException e) {
            System.out.println("Failed to clear data file: " + e.getMessage());
        }
    }

    // EFFECTS: exits program, AsK IF USER IS SURE, allows them to save or not
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ----------------------- EVENT METHODS --------------------------
    // EFFECTS: iterates through each event in the eventlog
    public void printAllEvents() {
        // iterates through each event in the EventLog
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString()); // Print the event details
        }
    }

    // EFFECTS: displays all the events that occured then quits the application
    @Override
    public void windowClosing(WindowEvent e) {
        printAllEvents();
        System.exit(0);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        displayMessage("WindowListener method called: windowActivated.");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        displayMessage("WindowListener method called: windowDeactivated.");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        displayMessage("WindowListener method called: windowOpened.");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        displayMessage("WindowListener method called: windowClosed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        displayMessage("WindowListener method called: windowIconified.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        displayMessage("WindowListener method called: windowDeiconified.");
    }

    void displayMessage(String msg) {
        textArea.append(msg + "\n");
    }

}

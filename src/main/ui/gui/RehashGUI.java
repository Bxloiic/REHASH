package main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.model.*;
import main.persistance.JsonWriter;
import main.ui.EventLog;
import main.ui.Event;

import java.util.*;
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
        try {
            // Create a JSON object to hold all data
            JSONObject data = new JSONObject();

            // Serialize hashes
            JSONArray hashesArray = new JSONArray();
            for (Hash hash : hashes.values()) {
                JSONObject hashObject = new JSONObject();
                hashObject.put("name", hash.getName());
                hashObject.put("type", hash.getType());
                hashObject.put("colour", hash.getColour());
                hashObject.put("material", hash.getMaterial());
                hashesArray.put(hashObject);
            }
            data.put("hashes", hashesArray);

            // Serialize hashdexes
            JSONArray hashdexesArray = new JSONArray();
            for (Hashdex hashdex : hashdexes.values()) {
                JSONObject hashdexObject = new JSONObject();
                hashdexObject.put("name", hashdex.getName());
                hashdexObject.put("colour", hashdex.getColour());

                JSONArray hashdexHashesArray = new JSONArray();
                for (Hash hash : hashdex.getHashList()) {
                    hashdexHashesArray.put(hash.getName());
                }
                hashdexObject.put("hashes", hashdexHashesArray);
                hashdexesArray.put(hashdexObject);
            }
            data.put("hashdexes", hashdexesArray);

            // serialize outfits
            JSONArray outfitsArray = new JSONArray();
            for (Outfit outfit : outfits.values()) {
                JSONObject outfitObject = new JSONObject();
                outfitObject.put("name", outfit.getName());

                // serializes the hashes in the outfit
                JSONArray outfitHashesArray = new JSONArray();
                for (Hash hash : outfit.getOutfitHashs()) {
                    outfitHashesArray.put(hash.getName());
                }
                outfitObject.put("hashes", outfitHashesArray);
                outfitsArray.put(outfitObject);
            }
            data.put("outfits", outfitsArray);

            // save to file
            FileWriter file = new FileWriter("./data/Rehash.json");
            file.write(data.toString(4)); // pretty print with indent of 4 spaces
            file.close();

            textArea.append("Data has been saved.\n");
        } catch (Exception e) {
            textArea.append("Error saving data: " + e.getMessage() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hashes, hashdex, and outfits from file
    private void loadData() {
        try {
            FileReader reader = new FileReader("./data/Rehash.json");
            StringBuilder content = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
            reader.close();

            // Parse the JSON data
            JSONObject data = new JSONObject(content.toString());

            // Delegate the loading tasks
            loadHashes(data);
            loadHashdexes(data);
            loadOutfits(data);

        } catch (Exception e) {
            textArea.append("Error loading data: " + e.getMessage() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads outfits from file
    public void loadOutfits(JSONObject data) {
        try {
            JSONArray outfitsArray = data.getJSONArray("outfits");
            for (int i = 0; i < outfitsArray.length(); i++) {
                JSONObject outfitObject = outfitsArray.getJSONObject(i);
                String name = outfitObject.getString("name");

                Outfit outfit = new Outfit(name);
                JSONArray outfitHashesArray = outfitObject.getJSONArray("hashes");
                for (int j = 0; j < outfitHashesArray.length(); j++) {
                    String hashName = outfitHashesArray.getString(j);
                    Hash hash = hashes.get(hashName);
                    if (hash != null) {
                        outfit.addHash(hash);
                    }
                }
                outfits.put(name, outfit);
            }

            textArea.append("Outfits loaded successfully.\n");
        } catch (Exception e) {
            textArea.append("Error loading outfits: " + e.getMessage() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hashes from file
    private void loadHashes(JSONObject data) {
        try {
            JSONArray hashesArray = data.getJSONArray("hashes");
            for (int i = 0; i < hashesArray.length(); i++) {
                JSONObject hashObject = hashesArray.getJSONObject(i);
                String name = hashObject.getString("name");
                String type = hashObject.getString("type");
                String colour = hashObject.getString("colour");
                String material = hashObject.getString("material");

                Hash hash = new Hash(name, type, colour, material);
                hashes.put(name, hash);
            }
            textArea.append("Hashes loaded successfully.\n");
        } catch (Exception e) {
            textArea.append("Error loading hashes: " + e.getMessage() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads hashdex from file
    private void loadHashdexes(JSONObject data) {
        try {
            JSONArray hashdexesArray = data.getJSONArray("hashdexes");
            for (int i = 0; i < hashdexesArray.length(); i++) {
                JSONObject hashdexObject = hashdexesArray.getJSONObject(i);
                String name = hashdexObject.getString("name");
                String colour = hashdexObject.getString("colour");

                Hashdex hashdex = new Hashdex(name, colour);
                JSONArray hashdexHashesArray = hashdexObject.getJSONArray("hashes");
                for (int j = 0; j < hashdexHashesArray.length(); j++) {
                    String hashName = hashdexHashesArray.getString(j);
                    Hash hash = hashes.get(hashName);
                    if (hash != null) {
                        hashdex.addHash(hash);
                    }
                }
                hashdexes.put(name, hashdex);
            }
            textArea.append("Hashdexes loaded successfully.\n");
        } catch (Exception e) {
            textArea.append("Error loading hashdexes: " + e.getMessage() + "\n");
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
            jsonWriter.write(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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
        // TODO Auto-generated method stub
        displayMessage("WindowListener method called: windowIconified.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        displayMessage("WindowListener method called: windowDeiconified.");
    }

    void displayMessage(String msg) {
        textArea.append(msg + "\n");
    }

}

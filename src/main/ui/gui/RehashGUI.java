package main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;

import main.model.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class RehashGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea; // for displaying info
    private HashMap<String, Hash> hashes;
    private HashMap<String, Hashdex> hashdexes;
    private HashMap<String, Outfit> outfits; // Store outfits by name
    private Week week;

    @SuppressWarnings("methodlength")
    public RehashGUI() {
        // Initialize collections to store data
        hashes = new HashMap<>();
        hashdexes = new HashMap<>();
        outfits = new HashMap<>();
        week = new Week();

        frame = new JFrame("Rehash GUI");
        panel = new JPanel();
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false); // User should not edit this

        panel.add(new JScrollPane(textArea)); // Add text area with scroll

        // Buttons to interact with the program
        JButton createHashButton = new JButton("Create a Hash");
        JButton createHashdexButton = new JButton("Create a Hashdex");
        JButton viewHashesButton = new JButton("View Hashes");
        JButton viewHashdexesButton = new JButton("View Hashdexes");
        JButton createOutfitButton = new JButton("Create an Outfit");
        JButton addHashToOutfitButton = new JButton("Add Hash to Outfit");
        JButton addHashToHashdexButton = new JButton("Add Hash to Hashdex");
        JButton viewOutfitsButton = new JButton("View Outfits");
        JButton saveDataButton = new JButton("Save Data");
        JButton loadDataButton = new JButton("Load Data");
        JButton clearDataButton = new JButton("Clear Data");
        JButton exitButton = new JButton("Exit");

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

        // Action listeners for each button
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    // Create a Hash
    private void createHash() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the item:");
        String type = JOptionPane.showInputDialog(frame, "Enter the type of the item:");
        String colour = JOptionPane.showInputDialog(frame, "Enter the colour of the item:");
        String material = JOptionPane.showInputDialog(frame, "Enter the material of the item:");

        Hash hash = new Hash(name, type, colour, material);
        hashes.put(name, hash);
        textArea.append("Created Hash: " + hash.getName() + "\n");
    }

    // Create a Hashdex
    private void createHashdex() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the Hashdex:");
        String colour = JOptionPane.showInputDialog(frame, "Enter the colour of the Hashdex:");

        Hashdex hashdex = new Hashdex(name, colour);
        hashdexes.put(name, hashdex);
        textArea.append("Created Hashdex: " + hashdex.getName() + "\n");
    }

    // View all Hashes

    private void viewHashes() {
        textArea.append("Displaying all hashes:\n");

        for (Hash hash : hashes.values()) {
            textArea.append("Hash: " + hash.getName() + ", Type: "
                    + hash.getType() + ", Colour: " + hash.getColour() + "\n");
        }
    }

    // View all Hashdexes
    private void viewHashdexes() {
        textArea.append("Displaying all hashdexes:\n");
        for (Hashdex hashdex : hashdexes.values()) {
            textArea.append("Hashdex: " + hashdex.getName() + ", Colour: " + hashdex.getColour() + "\n");
        }
    }

    // Create an Outfit
    private void createOutfit() {
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the outfit:");
        Outfit outfit = new Outfit(name);
        outfits.put(name, outfit);
        textArea.append("Created Outfit: " + outfit.getName() + "\n");
    }

    // Add Hash to Outfit
    private void addHashToOutfit() {
        String outfitName = JOptionPane.showInputDialog(frame, "Enter the name of the outfit to add the hash to:");
        String hashName = JOptionPane.showInputDialog(frame, "Enter the name of the hash to add:");

        Outfit outfit = outfits.get(outfitName);
        Hash hash = hashes.get(hashName);

        if (outfit != null && hash != null) {
            outfit.addHash(hash);
            textArea.append("Added Hash: " + hash.getName() + " to Outfit: " + outfit.getName() + "\n");
        } else {
            textArea.append("Invalid Outfit or Hash name\n");
        }
    }

    // Add Hash to Hashdex
    private void addHashToHashdex() {
        String hashName = JOptionPane.showInputDialog(frame, "Enter the name of the Hash:");
        String hashdexName = JOptionPane.showInputDialog(frame, "Enter the name of the Hashdex:");

        Hash hash = hashes.get(hashName);
        Hashdex hashdex = hashdexes.get(hashdexName);
        if (hash != null && hashdex != null) {
            hashdex.addHash(hash);
            textArea.append("Added Hash: " + hash.getName() + " to Hashdex: " + hashdex.getName() + "\n");
        } else {
            textArea.append("Invalid Hash or Hashdex name\n");
        }
    }

    // View all Outfits
    private void viewOutfits() {
        textArea.append("Displaying all outfits:\n");
        for (Outfit outfit : outfits.values()) {
            textArea.append("Outfit: " + outfit.getName() + "\n");
            for (Hash hash : outfit.getOutfitHashs()) {
                textArea.append("  - " + hash.getName() + "\n");
            }
        }
    }

    @SuppressWarnings("methodlength")
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
                hashdexesArray.put(hashdexObject);
            }
            data.put("hashdexes", hashdexesArray);

            // Serialize outfits
            JSONArray outfitsArray = new JSONArray();
            for (Outfit outfit : outfits.values()) {
                JSONObject outfitObject = new JSONObject();
                outfitObject.put("name", outfit.getName());

                // Serialize the hashes in the outfit
                JSONArray outfitHashesArray = new JSONArray();
                for (Hash hash : outfit.getOutfitHashs()) {
                    outfitHashesArray.put(hash.getName());
                }
                outfitObject.put("hashes", outfitHashesArray);
                outfitsArray.put(outfitObject);
            }
            data.put("outfits", outfitsArray);

            // Save to file
            FileWriter file = new FileWriter("./data/Rehash.json");
            file.write(data.toString(4)); // Pretty print with indent of 4 spaces
            file.close();

            textArea.append("Data has been saved.\n");
        } catch (Exception e) {
            textArea.append("Error saving data: " + e.getMessage() + "\n");
        }
    }

    @SuppressWarnings("methodlength")
    private void loadData() {
        try {
            // Read the JSON file
            FileReader reader = new FileReader("./data/Rehash.json");
            StringBuilder content = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
            reader.close();

            // Parse the JSON data
            JSONObject data = new JSONObject(content.toString());

            // Load hashes
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

            // Load hashdexes
            JSONArray hashdexesArray = data.getJSONArray("hashdexes");
            for (int i = 0; i < hashdexesArray.length(); i++) {
                JSONObject hashdexObject = hashdexesArray.getJSONObject(i);
                String name = hashdexObject.getString("name");
                String colour = hashdexObject.getString("colour");

                Hashdex hashdex = new Hashdex(name, colour);
                hashdexes.put(name, hashdex);
            }

            // Load outfits
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

            textArea.append("Data has been loaded.\n");
        } catch (Exception e) {
            textArea.append("Error loading data: " + e.getMessage() + "\n");
        }
    }

    // Clear Data
    private void clearData() {
        hashes.clear();
        hashdexes.clear();
        outfits.clear(); // Clear all outfits
        week = new Week(); // Reset the week
        textArea.append("All data has been cleared.\n");
    }

    // Exit Application
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}

package main.ui.gui;

// imports
import javax.swing.event.*;

import main.model.Hash;
import main.model.Hashdex;
import main.model.Outfit;
import main.ui.RMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;



public class RehashScreen extends JFrame implements ActionListener {

    // FIELDS
    private RMenu menu;
    private JLabel label;
    private JPanel redPanel;
    private JPanel bluePanel;
    private JPanel greenPanel;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private JButton clearDataButton;
    private JButton createHashButton;
    private JButton createHashdexButton;
    private JButton createOutfitButton;

    @SuppressWarnings("methodlength")
    public RehashScreen() {
        // Create menu object
        menu = new RMenu();
        
        // Image icon
        ImageIcon icon = new ImageIcon("/Users/loic/Downloads/volume-up.png");
        JLabel label = new JLabel();
        label.setIcon(icon);

        // Panels (sections of the frame)
        redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 1152, 50); // very top section
        redPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        redPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue); // middle section
        bluePanel.setBounds(0, 50, 1152, 200);
        bluePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bluePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        greenPanel = new JPanel(); // most bottom part
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 250, 1152, 388);
        greenPanel.setLayout(new BorderLayout());

        // Create a JTabbedPane, which will hold the tabs 
        JTabbedPane tabPanel = new JTabbedPane(); 

        // Tab 1: Display hashes
        JPanel page1 = new JPanel();
        page1.setLayout(new BorderLayout());
        JTextArea hashTextArea = new JTextArea();
        hashTextArea.setText(getHashesDisplay());
        hashTextArea.setEditable(false); // Make it non-editable
        JScrollPane hashScrollPane = new JScrollPane(hashTextArea);
        page1.add(hashScrollPane, BorderLayout.CENTER); // Add scrollable area
        tabPanel.addTab("Hashes", page1); 

        // Tab 2: Display outfits
        JPanel page2 = new JPanel();
        page2.setLayout(new BorderLayout());
        JTextArea outfitTextArea = new JTextArea();
        outfitTextArea.setText(getOutfitsDisplay());
        outfitTextArea.setEditable(false); // Make it non-editable
        JScrollPane outfitScrollPane = new JScrollPane(outfitTextArea);
        page2.add(outfitScrollPane, BorderLayout.CENTER);
        tabPanel.addTab("Outfits", page2);

        // Tab 3: Display hashdexes
        JPanel page3 = new JPanel();
        page3.setLayout(new BorderLayout());
        JTextArea hashdexTextArea = new JTextArea();
        hashdexTextArea.setText(getHashdexDisplay());
        hashdexTextArea.setEditable(false); // Make it non-editable
        JScrollPane hashdexScrollPane = new JScrollPane(hashdexTextArea);
        page3.add(hashdexScrollPane, BorderLayout.CENTER);
        tabPanel.addTab("Hashdexes", page3);

        // Add the JTabbedPane to the JFrame's content 
        greenPanel.add(tabPanel, BorderLayout.CENTER);

        // JFrame setup
        setTitle("REHASH");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1152, 648);

        // Containers
        add(redPanel);
        add(bluePanel);
        add(greenPanel);
        add(label); // adding icon to label
       
        saveDataButton();
        clearDataButton();
        loadDataButton();
        createHashButton();
        createHashdexButton();
        createOutfitButton();
        
        setVisible(true);
    }

    // Save, load, and clear buttons setup
    private void saveDataButton() {
        saveDataButton = new JButton();
        saveDataButton.setBounds(0, 0, 30, 15);
        saveDataButton.addActionListener(this);  // Register action listener
        saveDataButton.setText("save");
        redPanel.add(saveDataButton);  // Add the button to the frame
    }

    private void loadDataButton() {
        loadDataButton = new JButton();
        loadDataButton.setBounds(0, 0, 30, 15);
        loadDataButton.addActionListener(this);  // Register action listener
        loadDataButton.setText("load");
        redPanel.add(loadDataButton);  // Add the button to the frame
    }

    private void clearDataButton() {
        clearDataButton = new JButton();
        clearDataButton.setBounds(0, 0, 30, 15);
        clearDataButton.addActionListener(this);  // Register action listener
        clearDataButton.setText("clear");
        redPanel.add(clearDataButton);  // Add the button to the frame
    }

    private void createHashButton() {
        createHashButton = new JButton();
        createHashButton.setBounds(0, 0, 30, 15);
        createHashButton.addActionListener(this);  // Register action listener
        createHashButton.setText("Create Hash");
        bluePanel.add(createHashButton);  // Add the button to the frame
    }

    private void createHashdexButton() {
        createHashdexButton = new JButton();
        createHashdexButton.setBounds(0, 0, 30, 15);
        createHashdexButton.addActionListener(this);  // Register action listener
        createHashdexButton.setText("Create Hashdex");
        bluePanel.add(createHashdexButton);  // Add the button to the frame
    }

    private void createOutfitButton() {
        createOutfitButton = new JButton();
        createOutfitButton.setBounds(0, 0, 30, 15);
        createOutfitButton.addActionListener(this);  // Register action listener
        createOutfitButton.setText("Create Outfit");
        bluePanel.add(createOutfitButton);  // Add the button to the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveDataButton) {
            menu.saveData();
        } else if (e.getSource() == loadDataButton) {
            menu.loadData();
        } else if (e.getSource() == clearDataButton) {
            menu.clearData();
        } else if (e.getSource() == createOutfitButton) {
            menu.createOutfit();
        } else if (e.getSource() == createHashButton) {
            menu.createHash();
        } else if (e.getSource() == createHashdexButton) {
            menu.createHashdex();
        }
    }

    public String getHashesDisplay() {
        StringBuilder sb = new StringBuilder();
        if (menu.getHashes().isEmpty()) {
            sb.append("No hashes have been made yet!");
        } else {
            for (Hash h : menu.getHashes()) {
                sb.append("Hash Name: ").append(h.getName()).append("\n");
                sb.append("Details of this hash: \n");
                sb.append(h.getName()).append(":  Type: ").append(h.getType())
                  .append(", Colour: ").append(h.getColour())
                  .append(", Material: ").append(h.getMaterial())
                  .append(", Liked: ").append(h.getLiked()).append("\n\n");
            }
        }
        return sb.toString();
    }

    public String getHashdexDisplay() {
        StringBuilder sb = new StringBuilder();
        if (menu.getHashdexes().isEmpty()) {
            System.out.println("No hashdexes have been added yet!");
        }
        for (Hashdex h : menu.getHashdexes()) {
            sb.append("Hashdex Name: ").append(h.getName()).append("\n");
            if (h.getHashList().isEmpty()) {
                sb.append("No hashes have been added yet!\n");
            } else {
                sb.append("Items in this hashdex:\n");
                for (Hash hash : h.getHashList()) {
                    sb.append(hash.getName()).append(" (" + hash.getType() + ", " + hash.getColour() + ")\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getOutfitsDisplay() {
        StringBuilder sb = new StringBuilder();
        if (menu.getOutfits().isEmpty()) {
            sb.append("No outfits have been created yet!");
        } else {
            for (Outfit outfit : menu.getOutfits()) {
                sb.append("Outfit Name: ").append(outfit.getName()).append("\n");
                sb.append("Included items in this outfit:\n");
                for (Hash hash : outfit.getOutfitHashs()) {
                    sb.append("  - ").append(hash.getName()).append(" (" + hash.getType() + ", " 
                            + hash.getColour() + ")\n");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}









package main.ui.gui;


import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.IOException;
import javax.swing.*;
import java.io.File;
import main.ui.gui.RehashGUI;
import java.util.*;
import java.awt.*;


public class WelcomePage extends JFrame  {

    // FIELDS
    
    private Boolean isMuted = false;
    private Clip musicClip; // Store the current music clip

    // Constructor to set up GUI
    public WelcomePage() {
        initializeUI();
    }

    public void initializeUI() {
        setTitle("WELCOME"); // Title of window
        setSize(600, 600); // Size of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setLayout(null);
        
        JButton startButton = createStartButton();
        add(startButton);

        JLabel logoLabel = createBackground();
        add(logoLabel);

        playBackgroundMusic();
        setVisible(true);
    }

    // Method to set up the logo
    private JLabel createBackground() {
        // Load the animated GIF (make sure the path is correct)
        ImageIcon logoIcon = new ImageIcon("/Users/loic/Downloads/REHASH Mainscreen.gif");

        // Check if the image was loaded properly
        if (logoIcon.getIconWidth() == -1) {
            System.out.println("Error: Image not found!");
        }

        // Create a JLabel with the GIF as the icon
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 0, getWidth(), getHeight());
        logoLabel.setLayout(null);

        return logoLabel;
    }

    private JButton createStartButton() {
        // Load the start button image (replace with the correct path)
        ImageIcon startButtonIcon = new ImageIcon("/Users/loic/Downloads/startButton.png");
        
        // Create a button with the image as its icon
        JButton startButton = new JButton(startButtonIcon);
        
        // Make the button look transparent (no borders, background, etc.)
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        
        // Set the position and size of the button
        startButton.setBounds(280, 450, 48, 48); // x, y, width, height

        startButton.addActionListener(e -> showRehashGUI());

        return startButton;
    }

    // Method to show the RehashGUI screen
    private void showRehashGUI() {
        dispose();
        // display the RehashGUI screen
        new RehashGUI();
    }


    private void playBackgroundMusic() {
        // List of songs in your playlist (replace with the correct file paths)
        ArrayList<String> playlist = new ArrayList<>();
        //playlist.add("/Users/loic/Downloads/Dhruv_Grieving.wav");
        playlist.add("/Users/loic/Downloads/Frank_Ocean_Ivy.wav");
        playlist.add("/Users/loic/Downloads/Troy_Sivan.wav");
        playlist.add("/Users/loic/Downloads/Billie_Eilish.wav");
        playlist.add("/Users/loic/Downloads/Beach_Weather.wav");
        playlist.add("/Users/loic/Downloads/You get me so high.wav");

        // Randomly select a song from the playlist
        Random random = new Random();
        String selectedSong = playlist.get(random.nextInt(playlist.size()));

        // Play the selected song
       
    }





}

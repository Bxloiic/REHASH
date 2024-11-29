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
    private Clip musicClip; // store the current music clip

    // constructor to set up GUI
    public WelcomePage() {
        initializeUI();
    }

    public void initializeUI() {
        setTitle("WELCOME"); // title of window
        setSize(600, 600); // size of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the application when the window is closed
        setLayout(null);
        
        JButton startButton = createStartButton(); // creates start button
        add(startButton); // adds start button to frame

        JLabel logoLabel = createBackground(); // creates background
        add(logoLabel); // add background to frame

        //playBackgroundMusic(); // unfinished
        setVisible(true); // makes the window visible when ran
    }

    /**
     * EFFECTS: creates background using gif made
     * */
    private JLabel createBackground() {
        
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/REHASH Mainscreen.gif"));

        // checks if the image was loaded properly
        if (logoIcon.getIconWidth() == -1) {
            System.out.println("Error: Image not found!");
        }

        // creates a JLabel with the GIF as the icon
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 0, getWidth(), getHeight());
        logoLabel.setLayout(null);

        return logoLabel;
    }

    private JButton createStartButton() {

        ImageIcon startButtonIcon = new ImageIcon(getClass().getResource("/images/startButton.png"));
        
        // creates a button with the image as its icon
        JButton startButton = new JButton(startButtonIcon);
        
        // makes the button look transparent 
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        
        // sets the position and size of the button
        startButton.setBounds(280, 450, 48, 48); 

        startButton.addActionListener(e -> showRehashGUI());

        return startButton;
    }

    // Method to show the RehashGUI screen
    private void showRehashGUI() {
        dispose();
        // display the RehashGUI screen
        new RehashGUI();
    }


    // private void playBackgroundMusic() {

    //     ArrayList<String> playlist = new ArrayList<>();

    //     playlist.add("/Users/loic/Downloads/Frank_Ocean_Ivy.wav");
    //     playlist.add("/Users/loic/Downloads/Troy_Sivan.wav");
    //     playlist.add("/Users/loic/Downloads/Billie_Eilish.wav");
    //     playlist.add("/Users/loic/Downloads/Beach_Weather.wav");
    //     playlist.add("/Users/loic/Downloads/You get me so high.wav");

    //     // randomly select a song from the playlist
    //     Random random = new Random();
    //     String selectedSong = playlist.get(random.nextInt(playlist.size()));
       
    // }


}

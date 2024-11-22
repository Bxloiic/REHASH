package main.ui.gui;


import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainScreen extends JFrame  {

    // FIELDS
    
    private Boolean isMuted = false;
    private Clip musicClip; // Store the current music clip

    // Constructor to set up GUI
    public MainScreen() {
        initializeUI();
    }

    public void initializeUI() {
        setTitle("REHASH"); // Title of window
        setSize(1152, 648); // Size of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        
        // Private method used for internal GUI setup
        welcomePage();
        playBackgroundMusic(); // Start playing music
        addVolumeButton(); // Add mute button
    }

    // Method to set up the logo
    private void welcomePage() {
        // Load the animated GIF (make sure the path is correct)
        ImageIcon logoIcon = new ImageIcon("/Users/loic/Downloads/REHASH StoryBoard.gif");

        // Check if the image was loaded properly
        if (logoIcon.getIconWidth() == -1) {
            System.out.println("Error: Image not found!");
        }

        // Create a JLabel with the GIF as the icon
        JLabel logoLabel = new JLabel(logoIcon);

        // Add the label to the JFrame in the center
        add(logoLabel, BorderLayout.CENTER);
    }

    // Method to play background music from a playlist
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
        playSong(selectedSong);
    }

    // Method to play a single song and store the clip for muting
    private void playSong(String songPath) {
        try {
            // Load the audio file
            File audioFile = new File(songPath); // use the path of the selected song
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            musicClip = AudioSystem.getClip(); // Use the class field to store the clip
            musicClip.open(audioStream);
            musicClip.start(); // Start playing the audio

            
            //musicClip.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void addVolumeButton() {
        // Load the microphone icon (ensure the path is correct)
        ImageIcon microphoneIcon = new ImageIcon("/Users/loic/Downloads/volume-up.png");
        
        // Create a button with the microphone icon
        JButton volumeButton = new JButton(microphoneIcon);
        volumeButton.setBorderPainted(false); // Remove button border
        volumeButton.setFocusPainted(false); // Remove focus border
        volumeButton.setContentAreaFilled(false); // Make button transparent
    
        // Add an ActionListener to control the volume
        volumeButton.addActionListener(e -> adjustVolume());
    
        // Place the button on the JFrame (e.g., top-right corner)
        add(volumeButton, BorderLayout.NORTH); // Add directly to the JFrame
    }

    private void addVolumeButtonToLabel(JLabel logoLabel) {
        // Load the microphone icon (ensure the path is correct)
        ImageIcon microphoneIcon = new ImageIcon("/Users/loic/Downloads/volume-up.png");
        
        // Create a button with the microphone icon
        JButton volumeButton = new JButton(microphoneIcon);
        volumeButton.setBorderPainted(false); // Remove button border
        volumeButton.setFocusPainted(false); // Remove focus border
        volumeButton.setContentAreaFilled(false); // Make button transparent
    
        // Set the button's size and position relative to the JLabel
        volumeButton.setBounds(20, 20, 50, 50); // Adjust coordinates and size as needed
    
        // Add an ActionListener to control the volume
        volumeButton.addActionListener(e -> adjustVolume());
    
        // Add the button to the logoLabel
        logoLabel.add(volumeButton);
    }

    private void adjustVolume() {
        if (musicClip != null) {
            FloatControl volumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            
            // Example: Toggle between 50% and 100% volume
            float currentVolume = volumeControl.getValue();
            float targetVolume = (currentVolume < -10.0f) ? 0.0f : -10.0f; // Adjust dB levels
            volumeControl.setValue(targetVolume);
            System.out.println("Volume adjusted to: " + targetVolume);
        }
    }
    

}

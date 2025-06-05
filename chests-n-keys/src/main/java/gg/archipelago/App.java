package gg.archipelago;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gg.archipelago.view.LoginPanel;

/**
 * Hello world!
 */
public class App {
    /**
     * The window in which the app's GUI runs.
     */
    private final JFrame frame;

    /**
     * The client that connects to the Archipelago server.
     */
    private final ChestsNKeysClient client;

    public App() {
        frame = new JFrame();
        client = new ChestsNKeysClient();
        
        displayFrame();
    }

    private void displayFrame() {
        // Start by displaying the login menu in the frame.
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(loginPanel);

        // Set the frame up, and display it.
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End the application when the window is closed.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /*
         * Gonna be real, I don't understand why we have to use SwingUtilities.invokeLater().
         * Something about thread safety, I think, but do I even need concurrency?
         * Whatever, I'll use it just in case.
         */
        // Create the App
        SwingUtilities.invokeLater(() -> new App());
    }
}

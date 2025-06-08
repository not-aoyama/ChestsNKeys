package gg.archipelago;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gg.archipelago.network.ChestsNKeysClient;
import gg.archipelago.view.ChestsPanel;
import gg.archipelago.view.LoginPanel;

/**
 * Hello world!
 */
public class App {
    /**
     * The currently existing instance of the App class.
     * There should only be one App at a time.
     */
    private static App instance;

    /**
     * The window in which the app's GUI runs.
     */
    private final JFrame frame = new JFrame();

    /**
     * The menu used for logging into the server.
     */
    private LoginPanel loginPanel;

    /**
     * The client that connects to the Archipelago server.
     */
    private final ChestsNKeysClient client = new ChestsNKeysClient();

    public App() {
        /*
         * If there already is an instance of App, do nothing.
         */
        if (instance != null)
            return;
        
        // Mark this as the currently existing instance of App.
        instance = this;
        
        // Set up and display the window.
        displayFrame();
    }

    /**
     * Sets up and displays the GUI window for this App.
     * To start out with, the window will contain a login screen.
     */
    private void displayFrame() {
        // Start by displaying the login menu in the frame.
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        loginPanel = new LoginPanel();
        loginPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(loginPanel);

        // Set the frame up, and display it.
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End the application when the window is closed.
        frame.setVisible(true);
    }

    /**
     * Returns the ChestsNKeysClient of the app, or null if there is no app yet.
     * 
     * @return the ChestsNKeysClient of the app, or null if there is no app yet
     */
    public static ChestsNKeysClient getClient() {
        // Return null if there is no App yet for whatever reason.
        return (instance == null) ? null : instance.client;
    }

    /**
     * After the user finishes logging into the server, this method will be called. This method gets rid of the login
     * menu and displays the actual game itself.
     * 
     * @param numChests the number of chests in the game
     */
    public static void displayGame(int numChests) {
        JFrame appFrame = instance.frame;
        appFrame.remove(instance.loginPanel);
        appFrame.add(new ChestsPanel(numChests));
        appFrame.pack(); // Refresh the frame to show our changes.
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

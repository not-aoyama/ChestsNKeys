package gg.archipelago;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dev.koifysh.archipelago.ClientStatus;
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
     * The menu that displays all of the player's chests and the player's desk.
     */
    private ChestsPanel chestsPanel;

    /**
     * The message that is displayed when the player wins.
     */
    private final JLabel winMessage = new JLabel("U R WINNAR!!!1");

    /**
     * Whether or not the player has chosen to enable keys.
     * If this is true, then the chests are locked until their respective keys are received.
     * If this is false, then all the chests are unlocked from the beginning.
     */
    private boolean keysEnabled;

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
        instance.chestsPanel = new ChestsPanel(numChests);
        appFrame.add(instance.chestsPanel);
        refresh();
    }

    /**
     * If the player has reached their goal, this method will set the player's status in the multiworld to goaled and
     * display a win screen.
     */
    public static void displayWinMessageIfGoaled() {
        if (instance.client.hasGoaled()) {
            // Set the game state to goaled.
            instance.client.setGameState(ClientStatus.CLIENT_GOAL);

            JFrame appFrame = instance.frame;
            
            // Remove the login menu from the window. This does nothing if the login menu is no longer there.
            appFrame.remove(instance.loginPanel);

            // Remove the main game display from the window unless it hasn't been instantiated yet.
            if (instance.chestsPanel != null) {
                appFrame.remove(instance.chestsPanel);
            }

            /*
             * If a win message is already being displayed, remove it before adding a new one.
             * Otherwise, two win messages may be displayed at once.
             */
            appFrame.remove(instance.winMessage);

            // Display the win message!
            appFrame.add(instance.winMessage);
            refresh();
        }
    }

    /**
     * Refreshes the app window to show any new changes made to its contents.
     */
    public static void refresh() {
        instance.frame.pack();
    }

    public static boolean getKeysEnabled() {
        return instance.keysEnabled;
    }

    public static void setKeysEnabled(boolean keysEnabled) {
        instance.keysEnabled = keysEnabled;
    }

    /**
     * Updates the appearance of the Chest with the given number to reflect whether it has been checked and whether it
     * has been unlocked. If no chests are being displayed at the moment, this method does nothing.
     * 
     * @param chestNumber the number of the Chest to update. For example, if 2 is passed in, Chest 2 will be updated.
     */
    public static void updateChest(int chestsNumber) {
        if (instance.chestsPanel != null)
            instance.chestsPanel.updateChest(chestsNumber);
    }

    /**
     * Updates the appearance of the Desk to reflect whether it has been checked. If the Desk isn't being displayed at
     * the moment, this method does nothing.
     */
    public static void updateDesk() {
        if (instance.chestsPanel != null)
            instance.chestsPanel.updateDesk();
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

package gg.archipelago;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
        // Create a button. This is just for testing.
        JButton button = new JButton("Hello world!");
        button.setBounds(150, 250, 200, 50);

        // Add the button to the frame, set the frame up, and display it.
        frame.add(button);
        frame.setSize(500, 600);
        frame.setLayout(null); // use no layout managers
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

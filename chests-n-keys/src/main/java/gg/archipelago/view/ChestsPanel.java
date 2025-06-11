package gg.archipelago.view;

import javax.swing.JPanel;

import gg.archipelago.App;
import gg.archipelago.view.locations.Chest;
import gg.archipelago.view.locations.Desk;
import gg.archipelago.view.locations.Location;

/**
 * This is where the player's desk and chests are displayed.
 */
public class ChestsPanel extends JPanel {
    /**
     * An array containing all of the locations in the game.
     */
    private final Location[] locations;

    public ChestsPanel(int numChests) {
        /*
         * Initialize the list of locations.
         * The total number of locations is the number of chests plus 1 for the Desk.
         */
        locations = new Location[numChests + 1];

        // Initialize the Desk and store it at index 0.
        locations[0] = new Desk();

        // Initialize the chests and store them in the array as well.
        for (int i = 1; i <= numChests; i++) {
            locations[i] = new Chest(i);
        }

        // Add each of the locations to this panel.
        for (Location location : locations) {
            add(location);
        }

        // Refresh the window to show the locations!
        App.refresh();
    }

    /**
     * Updates the appearance of the Chest with the given number to reflect whether it has been checked and whether it
     * has been unlocked.
     * 
     * @param chestNumber the number of the Chest to update. For example, if 2 is passed in, Chest 2 will be updated.
     */
    public void updateChest(int chestNumber) {
        locations[chestNumber].update();
    }
}

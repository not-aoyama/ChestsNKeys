package gg.archipelago.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is where the player's desk and chests are displayed.
 */
public class ChestsPanel extends JPanel {
    public ChestsPanel(int numChests) {
        // For now, the chests panel just displays the number of chests.
        add(new JLabel("There are " + numChests + " chests."));
    }
}

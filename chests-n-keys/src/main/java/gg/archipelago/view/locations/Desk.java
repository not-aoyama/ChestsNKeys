package gg.archipelago.view.locations;

import gg.archipelago.App;
import gg.archipelago.network.ChestsNKeysClient;

public class Desk extends Location {
    public Desk() {
        // The ID is just 420000.
        super(ChestsNKeysClient.LOCATION_ID_PREFIX);
    }

    /**
     * Updates the Desk's appearance based on whether it's been checked.
     */
    @Override
    public void update() {
        // For now, the Desk is just represented by plain text. Will be replaced in the final release.
        if (isChecked()) {
            setText("No more free items.");
        } else {
            setText("Click for a free item!");
        }
    }

    @Override
    public void onClick() {
        // Attempt to check the Desk when it is clicked.
        check();

        // Refresh the window to show the update.
        App.refresh();
    }
}

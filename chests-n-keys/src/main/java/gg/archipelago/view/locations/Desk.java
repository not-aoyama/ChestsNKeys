package gg.archipelago.view.locations;

import gg.archipelago.App;

public class Desk extends Location {
    public Desk() {
        // The ID is just 420000.
        super(LOCATION_ID_PREFIX);
    }

    /**
     * Updates the Desk's appearance based on whether it's been checked.
     */
    @Override
    public void update() {
        if (isChecked()) {
            setText("No more free items.");
        } else {
            setText("Click for a free item!");
        }
    }

    @Override
    public void onClick() {
        // For now, just prints a debug message.
        System.out.println("The Desk was clicked!");
    }
}

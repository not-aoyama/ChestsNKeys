package gg.archipelago.view.locations;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import dev.koifysh.archipelago.ClientStatus;
import dev.koifysh.archipelago.LocationManager;
import gg.archipelago.App;

/**
 * Every location in the game is represented by a JLabel.
 */
public abstract class Location extends JLabel {
    /**
     * The number that the server uses to represent this location.
     */
    private final Long locationId;

    /**
     * Creates a new location with the given numerical location ID.
     * 
     * @param locationId the number that the server uses to represent this location
     */
    public Location(Long locationId) {
        this.locationId = locationId;

        // Call onClick() whenever this location is clicked.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
            }
        });

        // Call update() for the first time to determine the appearance.
        update();
    }

    /**
     * Returns whether this location has been checked yet.
     * 
     * @return whether this location has been checked yet
     */
    public boolean isChecked() {
        return App.getClient().getLocationManager().getCheckedLocations().contains(locationId);
    }

    /**
     * If this location hasn't been checked yet, checks this location. and updates its appearance to match.
     * Also sets the game status to goal and displays the win screen if all locations have been checked.
     */
    public void check() {
        LocationManager locationManager = App.getClient().getLocationManager();

        if (!isChecked()) {
            locationManager.checkLocation(locationId);
            update();

            // Check if the goal has been achieved, and if so, display the win screen.
            App.displayWinMessageIfGoaled();
        }
    }

    /**
     * Updates this Location's appearance and tooltip to match whether it has been checked or not.
     * This method should be called whenever the game starts up, whenever this Location is checked, and whenever a new
     * item is received.
     */
    public abstract void update();

    /**
     * This method is called whenever this Location is clicked on.
     * The default behavior is to 
     */
    protected abstract void onClick();
}

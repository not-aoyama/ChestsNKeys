package gg.archipelago.network;

import java.util.HashMap;

import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.ConnectionResultEvent;
import dev.koifysh.archipelago.events.Event;
import gg.archipelago.App;

/**
 * This class contains methods that handle varions ArchipelagoEvents.
 * These methods are all tagged with `@ArchipelagoEventListener`.
 * Every time an event occurs, its corresponding method is automatically called.
 */
public class EventListener {
    private final ChestsNKeysClient client;

    public EventListener(ChestsNKeysClient client) {
        this.client = client;
    }

    @ArchipelagoEventListener
    public void onConnectionResult(ConnectionResultEvent event) {
        /*
         * Figure out how many chests there are.
         * This can be done by counting how many missing locations and checked locations there are,
         * then subtracting 1 because all locations except the Desk are chests.
         */
        int numCheckedLocations = client.getLocationManager().getCheckedLocations().size();
        int numMissingLocations = client.getLocationManager().getMissingLocations().size();
        int numChests = numCheckedLocations + numMissingLocations - 1;

        // Hide the login menu and display the actual game!
        App.displayGame(numChests);
    }
}

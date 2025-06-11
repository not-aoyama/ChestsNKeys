package gg.archipelago.network;

import java.util.HashMap;

import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.ConnectionResultEvent;
import dev.koifysh.archipelago.events.ReceiveItemEvent;
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

        /*
         * Figure out whether keys are enabled. This is listed in the slot data under the parameter keys_enabled.
         * If keys are enabled, keys_enabled is 1.0. If keys are disabled, keys_enabled is 0.0.
         */
        Object keysEnabledObj = event.getSlotData(HashMap.class).get("keys_enabled");
        try {
            Double keysEnabled = (Double)keysEnabledObj;
            // int keysEnabled = Integer.parseInt(keysEnabledObj.toString());
            switch (keysEnabled.intValue()) {
                case 0:
                    App.setKeysEnabled(false);
                    break;
                case 1:
                    App.setKeysEnabled(true);
                    break;
                default:
                    throw new InvalidSlotDataException("Invalid slot data; \"keys_enabled\" should be 0.0 or 1.0 but is " + keysEnabled);
            }
        } catch (NumberFormatException | ClassCastException ex) {
            throw new InvalidSlotDataException("Invalid slot data; \"keys_enabled\" should be 0.0 or 1.0 but is " + keysEnabledObj);
        }

        // Hide the login menu and display the actual game!
        App.displayGame(numChests);
    }

    @ArchipelagoEventListener
    public void onReceiveItem(ReceiveItemEvent event) {
        /*
         * If the item received is a key, update its corresponding Chest in the ChestsPanel.
         * Every item is a key, except for the Item That Does Nothing, which has ID 69420.
         */
        if (event.getItemID() != ChestsNKeysClient.ITEM_THAT_DOES_NOTHING_ID) {
            /*
             * We can find a key's number via the last three digits of its ID.
             * For instance, Key 1 has ID 69001, and Key 256 has ID 69256.
             */
            int keyNumber = (int)(event.getItemID() - ChestsNKeysClient.ITEM_ID_PREFIX);
            App.updateChest(keyNumber);
        }
    }
}

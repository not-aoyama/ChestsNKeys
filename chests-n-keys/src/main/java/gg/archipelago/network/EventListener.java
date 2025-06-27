package gg.archipelago.network;

import java.util.HashMap;

import dev.koifysh.archipelago.ClientStatus;
import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.CheckedLocationsEvent;
import dev.koifysh.archipelago.events.ConnectionResultEvent;
import dev.koifysh.archipelago.events.ReceiveItemEvent;
import gg.archipelago.App;

/**
 * This class contains methods that handle varions ArchipelagoEvents.
 * These methods are all tagged with `@ArchipelagoEventListener`.
 * Every time an event occurs, its corresponding method is automatically called.
 */
public class EventListener {
    @ArchipelagoEventListener
    public void onConnectionResult(ConnectionResultEvent event) {
        ChestsNKeysClient client = App.getClient();

        /*
         * If the player has already goaled, skip to the win screen.
         * Otherwise, display the main game screen.
         */
        if (client.hasGoaled()) {
            client.setGameState(ClientStatus.CLIENT_GOAL);
            App.displayWinMessage();
        } else {
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
    }

    @ArchipelagoEventListener
    public void onCheckedLocations(CheckedLocationsEvent event) {
        // Update every location's onscreen apperance to reflect that it has been checked.
        for (Long locationId : event.checkedLocations) {
            /*
             * For some reason, when this event occurs, the locations that have been checked are NOT in the list of
             * checked locations. If we don't add them in, the locations will still look like they haven't been
             * checked.
             */
            App.getClient().getLocationManager().getCheckedLocations().add(locationId);
            App.getClient().getLocationManager().getMissingLocations().remove(locationId);

            // Get the last three digits of the location ID.
            long locationIdSuffix = locationId - ChestsNKeysClient.LOCATION_ID_PREFIX;

            // If the last three digits are 000, the location that's been checked is the Desk.
            if (locationIdSuffix == 0) {
                App.updateDesk();
            }
            /*
             * Otherwise, the location that's been updated is the Chest whose number equals the last three digits.
             * E.G. location ID 420001 corresponds to Chest 1.
             */
            else {
                App.updateChest((int)locationIdSuffix);
            }
        }
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

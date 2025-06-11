package gg.archipelago.view.locations;

import gg.archipelago.App;
import gg.archipelago.network.ChestsNKeysClient;

/**
 * Each game of Chests 'n' Keys contains anywhere from 1 to 256 Chests that the player must open. Each Chest is
 * represented by a JLabel containing an image of a chest. When the user clicks on the JLabel of a closed chest, the
 * chest is opened!
 */
public class Chest extends Location {
    /**
     * What number chest this chest is. The number can be anywhere from 1 to 256.
     * This number is different from the chest's location ID!
     */
    private final int number;

    public Chest(int number) {
        super(ChestsNKeysClient.LOCATION_ID_PREFIX + number); // Use the chest number to determine the location ID
        this.number = number;

        /*
         * We have to run update() again.
         * The first time it was ran was in super(), which is before we instantiated the number of this Chest.
         */
        update();
    }

    /**
     * Returns whether this chest is unlocked.
     * If the chest is unlocked, that means it can be checked.
     * The chest is unlocked if and only if the player has obtained its key OR keys are disabled.
     * 
     * @return whether this chest is unlocked
     */
    private boolean isUnlocked() {
        // Has the player received this chest's key? If so, the chest is unlocked.
        long keyId = ChestsNKeysClient.ITEM_ID_PREFIX + number;
        if (App.getClient().getItemManager().getReceivedItemIDs().contains(keyId)) {
            return true;
        }

        // If the player doesn't have the key, the chest is only unlocked if keys are disabled.
        return !App.getKeysEnabled();
    }

    /**
     * Updates this Chests's appearance and tooltip to match whether it has been checked or not, and whether it has
     * been unlocked. This method should be called whenever the game starts up, whenever this Chest is checked, and
     * whenever a new item is received.
     */
    @Override
    public void update() {
        // For now, set plain text instead of an image. This will be changed eventually.
        // If this chest has been checked already, display that this chest is empty.
        if (isChecked()) {
            setText("Chest " + number + "\n(Empty)");
        }
        // If this chest is unchecked and unlocked, display that this chest is unlocked.
        else if (isUnlocked()) {
            setText("Chest " + number + "\n(Unlocked)");
        }
        // If this chest is locked, display that its locked.
        else {
            setText("Chest " + number + "\n(Locked)");
        }
    }

    @Override
    public void onClick() {
        /*
         * If this chest is unlocked, try to check it.
         * This will only do anything if the chest hasn't already been checked.
         */
        if (isUnlocked()) {
            check();

            // Refresh the window to show the update.
            App.refresh();
        }
    }
}

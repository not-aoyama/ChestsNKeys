package gg.archipelago.network;

import dev.koifysh.archipelago.Client;
import dev.koifysh.archipelago.flags.ItemsHandling;

public class ChestsNKeysClient extends Client {
    /**
     * Every Chests 'n' Keys item ID in the server has five digits, the first two of which are 69. (Haha I'm so funny).
     * This number will be very helpful for obtaining the item IDs of Keys.
     */
    public static final long ITEM_ID_PREFIX = 69_000;

    /**
     * The item ID of the Item That Does Nothing.
     */
    public static final long ITEM_THAT_DOES_NOTHING_ID = 69_420;

    /**
     * Every Chests 'n' Keys location ID in the server has six digits, the first three of which are 420. (Haha I'm so
     * funny). This number will be very helpful to subclasses of Location when passing in the location ID.
     */
    public static final long LOCATION_ID_PREFIX = 420_000;

    public ChestsNKeysClient() {
        // Set the name of the game
        setGame("Chests 'n' Keys");

        // Create and register an EventListener.
        getEventManager().registerListener(new EventListener(this));

        // Have the server notify us of all received items.
        setItemsHandlingFlags(ItemsHandling.SEND_ITEMS + ItemsHandling.SEND_OWN_ITEMS + ItemsHandling.SEND_STARTING_INVENTORY);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("\nFrom ChestNKeysClient.onError()");
        ex.printStackTrace();
    }

    @Override
    public void onClose(String Reason, int attemptingReconnect) {
        System.out.println("\nFrom ChestNKeysClient.onClose()");
        System.out.println("Connection closed for reason: " + Reason);
    }
}

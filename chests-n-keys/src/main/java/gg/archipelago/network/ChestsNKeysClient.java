package gg.archipelago.network;

import dev.koifysh.archipelago.Client;
import dev.koifysh.archipelago.flags.ItemsHandling;

public class ChestsNKeysClient extends Client {

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

package gg.archipelago;

import dev.koifysh.archipelago.Client;
import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.ConnectionResultEvent;

public class ChestsNKeysClient extends Client {

    public ChestsNKeysClient() {
        // Set the name of the game
        setGame("Chests 'n' Keys");
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

    @ArchipelagoEventListener
    private void onConnectionResult(ConnectionResultEvent e) {
        // Debug code: print out the event, the room info, and the lists of checked locations, missing locations, and items.
        System.out.println("ConnectionResultEvent: " + e);
        System.out.println("Room info:");
        System.out.println(client.getRoomInfo());
        System.out.println("\nList of received items:");
        client.getItemManager().getReceivedItems().stream().forEach(System.out::println);
        System.out.println("\nList of checked locations:");
        client.getLocationManager().getCheckedLocations().stream().forEach(System.out::println);
        System.out.println("\nList of missing locations:");
        client.getLocationManager().getMissingLocations().stream().forEach(System.out::println);
    }
}

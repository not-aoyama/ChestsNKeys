package gg.archipelago;

import dev.koifysh.archipelago.Client;

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
    
}

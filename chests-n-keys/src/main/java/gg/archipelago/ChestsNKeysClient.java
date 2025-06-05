package gg.archipelago;

import dev.koifysh.archipelago.Client;

public class ChestsNKeysClient extends Client {

    public ChestsNKeysClient() {
        setGame("Chests 'n' Keys");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onClose(String Reason, int attemptingReconnect) {
        System.out.println("Connection closed for reason: " + Reason);
    }
    
}

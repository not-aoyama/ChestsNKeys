package gg.archipelago.network;

import java.util.HashMap;

import dev.koifysh.archipelago.events.ArchipelagoEventListener;
import dev.koifysh.archipelago.events.ConnectionResultEvent;
import dev.koifysh.archipelago.events.Event;

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
        // Debug code: print out the slot data
        System.out.println(event.getSlotData(HashMap.class));
    }
}

package gg.archipelago.network;

/**
 * This exception is thrown if the slot data is invalid.
 */
public class InvalidSlotDataException extends RuntimeException {
    public InvalidSlotDataException(String message) {
        super(message);
    }
}

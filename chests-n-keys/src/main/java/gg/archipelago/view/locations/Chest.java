package gg.archipelago.view.locations;

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
        super(LOCATION_ID_PREFIX + number); // Use the chest number to determine the location ID
        this.number = number;
        
        /*
         * We have to run update() again.
         * The first time it was ran was in super(), which is before we instantiated the number of this Chest.
         */
        update();
    }

    @Override
    public void update() {
        // For now, set default text instead of an image. This will be changed eventually.
        setText("Chest " + number);
    }

    @Override
    public void onClick() {
        // For now, just prints a debug message.
        System.out.println("Chest " + number + " was clicked!");
    }
}

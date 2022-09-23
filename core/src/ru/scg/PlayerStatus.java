package ru.scg;

public final class PlayerStatus {

    private static short health = 50; // Game params 0 <= n <= 100
    private static short mental = 50;
    private static short study = 50;
    private static short money = 50;

    private static final short DEATH = 1;
    private static final short INSANITY = 2;
    private static final short DROPPEDOUT = 3;
    private static final short BROKE = 4;



    private PlayerStatus () {}

    private static int checkStatus() {
        if (health <= 0) return DEATH;
        return 0;
    }

    /**
     *
     * @param data - Formatted string "-1 5 10 -15"
     */
    public static void update (String data) {
        String[] s = data.split(" ");
        health += Integer.parseInt(s[0]);
        mental += Integer.parseInt(s[1]);
        study += Integer.parseInt(s[2]);
        money += Integer.parseInt(s[3]);
    }

    public static void update (int[] data) {
        health += data[0];
        mental += data[1];
        study += data[2];
        money += data[3];
    }

}

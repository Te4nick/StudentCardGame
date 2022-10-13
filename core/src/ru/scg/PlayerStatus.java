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

    private static short getStatus() {
        if (health <= 0) return DEATH;
        else if (mental <= 0) return INSANITY;
        else if (study <= 0) return DROPPEDOUT;
        else if (money <= 0) return BROKE;
        return 0;
    }

    public static void update(short[] data) {
        health += data[0];
        mental += data[1];
        study += data[2];
        money += data[3];
    }

    public static short[] getParams() {
        return new short[]{health, mental, study, money};
    }

}

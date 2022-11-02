package ru.scg;

public final class PlayerStatus {

    private static short health = 50; // Game params 0 <= n <= 100
    private static short mental = 50;
    private static short study = 50;
    private static short money = 50;

    public static final short DEATH = 1;
    public static final short INSANITY = 2;
    public static final short DROPPED = 3;
    public static final short BROKE = 4;

    private PlayerStatus () {}

    public static short getStatus() {
        if (health <= 0) return DEATH;
        else if (mental <= 0) return INSANITY;
        else if (study <= 0) return DROPPED;
        else if (money <= 0) return BROKE;
        return 0;
    }

    public static short update(short[] data) {
        health = (short) ((health + data[0]) % 101);
        mental = (short) ((mental + data[1]) % 101);
        study = (short) ((study + data[2]) % 101);
        money = (short) ((money + data[3]) % 101);
        return getStatus();
    }

    public static short[] getStats() {
        return new short[]{health, mental, study, money};
    }


}

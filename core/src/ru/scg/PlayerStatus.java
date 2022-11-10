package ru.scg;

public final class PlayerStatus {

    private static byte health = 0; // Game params 1 <= n <= 100
    private static byte mental = 0;
    private static byte study = 0;
    private static byte money = 0;
    private static short duration = 0;
    public static final short ZEROPARAM1 = 1;
    public static final short ZEROPARAM2 = 2;
    public static final short ZEROPARAM3 = 3;
    public static final short ZEROPARAM4 = 4;

    private PlayerStatus () {}

    public static void resetStats() {
        health = 50;
        mental = 50;
        study = 50;
        money = 50;
        duration = 0;
    }

    public static short getStatus() {
        if (health == 0) return ZEROPARAM1;
        else if (mental == 0) return ZEROPARAM2;
        else if (study == 0) return ZEROPARAM3;
        else if (money == 0) return ZEROPARAM4;
        return 0;
    }

    public static short update(byte[] data) {
        health += data[0];
        if (health > 100) health = 100;
        if (health < 0) health = 0;
        mental += data[1];
        if (mental > 100) mental = 100;
        if (mental < 0) mental = 0;
        study += data[2];
        if (study > 100) study = 100;
        if (study < 0) study = 0;
        money += data[3];
        if (money > 100) money = 100;
        if (money < 0) money = 0;
        return getStatus();
    }

    public static int getDuration() {
        return duration;
    }

    public static void incrementDuration() {
        duration++;
    }

    public static void setDuration(short duration) {
        PlayerStatus.duration = duration;
    }

    public static short[] getStats() {
        return new short[]{health, mental, study, money};
    }

}

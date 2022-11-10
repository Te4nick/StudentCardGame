package ru.scg;

public final class PlayerStatus {

    public static short getHealth() {
        return health;
    }

    public static short getMental() {
        return mental;
    }

    public static short getStudy() {
        return study;
    }

    public static short getMoney() {
        return money;
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        PlayerStatus.duration = duration;
    }
    private static short health = 0; // Game params 0 <= n <= 100
    private static short mental = 0;
    private static short study = 0;
    private static short money = 0;

    private static int duration = 0;
    public static final short DEATH = 1;
    public static final short INSANITY = 2;
    public static final short DROPPEDOUT = 3;
    public static final short BROKE = 4;

    private PlayerStatus () {}

    public static short getStatus() {
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
    public static void set(short[] data) {
        health = data[0];
        mental = data[1];
        study = data[2];
        money = data[3];
    }

    public static short[] getParams() {
        return new short[]{health, mental, study, money};
    }


}

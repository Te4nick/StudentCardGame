package ru.scg;

public class Card {

    private final String spritePath;
    private final String character;
    private final String lineM;
    private final String lineL;
    private final String lineR;
    private final short[] statsL;
    private final short[] statsR;
    private final String nextCardL;
    private final String nextCardR;

    Card(String spritePath, String character, String lineM, String lineL, String lineR, short[] statsL, short[] statsR, String nextCardL, String nextCardR) {
        this.spritePath = spritePath;
        this.character = character;
        this.lineM = lineM;
        this.lineL = lineL;
        this.lineR = lineR;
        this.statsL = statsL.clone();
        this.statsR = statsR.clone();
        this.nextCardL = nextCardL;
        this.nextCardR = nextCardR;
    }

    public String getLineM() {
        return lineM;
    }

    public String getLineL() {
        return lineL;
    }

    public String getLineR() {
        return lineR;
    }

    public short[] getStatsL() {
        return statsL;
    }
    public short[] getStatsR() {
        return statsR;
    }

    @Override
    public String toString() {
        return "M: " + lineM +
                "\nL: " + lineL +
                "\nR: " + lineR +
                "\nCharacter: " + character +
                "\nSprite: " + spritePath +
                "\nStatsL: [ " + statsL[0] + " " + statsL[1] + " " + statsL[2] + " " + statsL[3] + " ]" +
                "\nStatsR: [ " + statsR[0] + " " + statsR[1] + " " + statsR[2] + " " + statsR[3] + " ]";
    }

    public String getNextCardL() {
        return nextCardL;
    }

    public String getNextCardR() {
        return nextCardR;
    }
}

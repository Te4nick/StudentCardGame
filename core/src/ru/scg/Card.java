package ru.scg;

public class Card {

    private final String spritePath;
    private final String character;
    private final String lineM;
    private final String lineL;
    private final String lineR;
    private final int[] statsL;
    private final int[] statsR;

    Card(String spritePath, String character, String lineM, String lineL, String lineR, int[] statsL, int[] statsR) {
        this.spritePath = spritePath;
        this.character = character;
        this.lineM = lineM;
        this.lineL = lineL;
        this.lineR = lineR;
        this.statsL = statsL.clone();
        this.statsR = statsR.clone();
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

    public int[] getStatsL() {
        return statsL;
    }
    public int[] getStatsR() {
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
}

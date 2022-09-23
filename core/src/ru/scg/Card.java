package ru.scg;

public class Card {
    private final String lineM;
    private final String lineL;
    private final String LineR;

    Card(String lineM, String lineL, String LineR) {
        this.lineM = lineM;
        this.lineL = lineL;
        this.LineR = LineR;
    }

    public String getLineM() {
        return lineM;
    }

    public String getLineL() {
        return lineL;
    }

    public String getLineR() {
        return LineR;
    }
}

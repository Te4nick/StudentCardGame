package ru.sgc;

public class Card {
    private String frameText;
    private String leftText;
    private String rightText;

    Card(String frameText, String leftText, String rightText) {
        this.frameText = frameText;
        this.leftText = leftText;
        this.rightText = rightText;
    }

    public String getFrameText() {
        return frameText;
    }

    public String getLeftText() {
        return leftText;
    }

    public String getRightText() {
        return rightText;
    }
}

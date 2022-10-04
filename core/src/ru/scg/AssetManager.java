package ru.scg;

import java.util.HashMap;

public final class AssetManager {

    private static String[] cardKeys;
    public static HashMap<String, Card> buildDeck() {
        HashMap<String, Card> d = new HashMap<>();
        int[] statsL = new int[4], statsR = new int[4];
        String[] lines, stats;
        cardKeys = Localizator.getString("cardKeys").split(" ");
        for (String k : cardKeys) {
            lines = Localizator.getString(k).split("\n");
            stats = lines[5].split(" ");
            for (int i = 0; i < 8; i++) {
                if (i < 4) statsL[i] = Integer.parseInt(stats[i]);
                else statsR[i-4] = Integer.parseInt(stats[i]);
            }
            d.put(k, new Card(lines[0], lines[1], lines[2], lines[3], lines[4], statsL, statsR));
        }
        return d;
    }

    public static String[] getCardKeys() {
        if (cardKeys[0].isEmpty()) cardKeys = Localizator.getString("cardKeys").split(" ");
        return cardKeys;
    }

}

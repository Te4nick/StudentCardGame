package ru.scg;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public final class AssetManager {

    private static String[] cardKeys;
    private static Card currentCard;

    public static HashMap<String, Card> buildDeck() {
        HashMap<String, Card> d = new HashMap<>();
        byte[] statsL = new byte[4], statsR = new byte[4];
        String[] lines, stats;
        cardKeys = Localizator.getString("cardKeys").split(" ");
        for (String k : cardKeys) {
            lines = Localizator.getString(k).split("\n");
            stats = lines[5].split(" ");
            for (int i = 0; i < 8; i++) {
                if (i < 4) statsL[i] = Byte.parseByte(stats[i]);
                else statsR[i-4] = Byte.parseByte(stats[i]);
            }
            d.put(k, new Card(lines[0], lines[1], lines[2], lines[3], lines[4], statsL, statsR, lines[6], lines[7]));
        }
        return d;
    }

    public static String[] getCardKeys() {
        if (cardKeys == null) cardKeys = Localizator.getString("cardKeys").split(" ");
        return cardKeys;
    }

    public static Card getCard(String key) { // NOTE: run getCardKeys() first
        byte[] statsL = new byte[4], statsR = new byte[4];
        String[] lines, stats;
        lines = Localizator.getString(key).split("\n");
        stats = lines[5].split(" ");
        for (int i = 0; i < 8; i++) {
            if (i < 4) statsL[i] = Byte.parseByte(stats[i]);
            else statsR[i-4] = Byte.parseByte(stats[i]);
        }
        currentCard = new Card(lines[0], lines[1], lines[2], lines[3], lines[4], statsL, statsR, lines[6], lines[7]);
        return currentCard;
    }

    public static Card getNextCard(boolean leftOption) { // NOTE: run getCardKeys() first
        String[] defaultEndings = Localizator.getString("defaultEndings").split(" "); String key;
        switch (PlayerStatus.getStatus()) {
            case PlayerStatus.ZEROPARAM1:
                key = defaultEndings[0];
                break;
            case PlayerStatus.ZEROPARAM2:
                key = defaultEndings[1];
                break;
            case PlayerStatus.ZEROPARAM3:
                key = defaultEndings[2];
                break;
            case PlayerStatus.ZEROPARAM4:
                key = defaultEndings[3];
                break;
            default:
                if (leftOption) key = currentCard.getNextCardL();
                else key = currentCard.getNextCardR();
                break;
        }
        return getCard(key);
    }

    public static void startTextGame() {
        getCardKeys();
        Card c = getCard(cardKeys[0]);
        Random r = new Random();
        int cardKeysLength = cardKeys.length;
        boolean run = true;
        String choice;
        Scanner sc = new Scanner(System.in);
        drawTUI(c);
        while (run) {
            choice = sc.next();
            if (choice.equals("l")) {
                if (c.getNextCardL().equals("end")) run = false;
                else if (c.getNextCardL().equals("any")) {
                    PlayerStatus.update(c.getStatsL());
                    c = getCard(cardKeys[r.nextInt(cardKeysLength - 1)]);
                } else {
                    PlayerStatus.update(c.getStatsL());
                    c = getCard(c.getNextCardL());
                }
            }
            else if (choice.equals("r")) {
                if (c.getNextCardR().equals("end")) run = false;
                else if (c.getNextCardR().equals("any")) {
                    PlayerStatus.update(c.getStatsR());
                    c = getCard(cardKeys[r.nextInt(cardKeysLength - 1)]);
                } else {
                    PlayerStatus.update(c.getStatsR());
                    c = getCard(c.getNextCardR());
                }
            }
            switch (PlayerStatus.getStatus()) {
                case PlayerStatus.ZEROPARAM1:
                    c = getCard("zeroParam1");
                    break;
                case PlayerStatus.ZEROPARAM2:
                    c = getCard("zeroParam2");
                    break;
                case PlayerStatus.ZEROPARAM3:
                    c = getCard("zeroParam3");
                    break;
                case PlayerStatus.ZEROPARAM4:
                    c = getCard("zeroParam4");
                    break;
                default:
                    break;
            }
            drawTUI(c);
        }
    }

    private static void drawTUI(Card c) {
        short[] p = PlayerStatus.getStats();
        System.out.printf("Health: %d, Mental: %d, Study: %d, Money: %d\n", p[0], p[1], p[2], p[3]);
        System.out.println(c.toString() + "\nChoose left/right option (Enter \"l\" or \"r\"):");
    }

    public static boolean isEnd(/*String s*/) {
//        if (endings[0].isEmpty()) endings = Localizator.getString("endings").split(" ");
//        for (String e : endings) if (e.equals(s)) return true;
//        return false;
        return PlayerStatus.getStatus() != 0;
    }

}

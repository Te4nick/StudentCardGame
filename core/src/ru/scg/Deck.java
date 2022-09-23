package ru.scg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Deck {

    private HashMap<String, Card> cardDeck; // Deck.cardDeck.put( "key", new Card(lm, ll, lr));

    public Deck() {

    }

//    private void buildDeck() {
//        Map<String, String> cardLines = Map.of(
//                "wu_m", "You woke up in your bed.", // Wake up card
//                "wu_l", "Naaah, lemme sleep more!",
//                "wu_r", "Well, time to wake up...",
//                "bf_m", "Should I have a breakfast?", // Breakfast card
//                "bf_l", "Yeah, nice idea.",
//                "bf_r", "Not in the mood rn"
//        );
//        Set<String> cardKeys = new HashSet<>(Arrays.asList("wu", "bf"));
//        for (String key : cardKeys) {
//            cardDeck.put(key, new Card(cardLines.get(key + "_m"), cardLines.get(key + "_l"), cardLines.get(key + "_r")));
//        }
//    }

    public void put(String cardKey, String lineM, String lineL, String lineR) {
        cardDeck.put(cardKey, new Card(lineM, lineL, lineR));
    }

    /**
     * Adds all cards by provided strings. NOTICE: parameters count must divide by 4.
     * @param cardKey - Card key string
     * @param lineM - Middle card line
     * @param lineL - Left card line
     * @param lineR - Right card line
     * @param lines - String list of [cardKey, lineM, lineL, lineR] values
     */
    public void putAll(String cardKey, String lineM, String lineL, String lineR, String[] lines) {
        cardDeck.put(cardKey, new Card(lineM, lineL, lineR));
        for (int i = 0; i < lines.length; i+=4) {
            cardDeck.put(lines[i], new Card(lines[i+1], lines[i+2], lines[i+3]));
        }
    }

    public void remove(String cardKey) {
        cardDeck.remove(cardKey);
    }

    public void removeAll(String[] cardKeys) {
        for (String key : cardKeys) {
            cardDeck.remove(key);
        }
    }

    public String[] get(String cardKey) {
        Card c = cardDeck.get(cardKey);
        return new String[]{c.getLineM(), c.getLineL(), c.getLineR()};
    }


}

package si.afridau.blackjack;

import si.afridau.blackjack.core.Card;

public class DeckUtil {
    private DeckUtil() {}

    private static final int DECK_SIZE = Card.deck.length;

    public static int getDeckSize() {
        return DECK_SIZE;
    }

    public static int getTotalCards(int decks) {
        return DECK_SIZE * decks;
    }
}

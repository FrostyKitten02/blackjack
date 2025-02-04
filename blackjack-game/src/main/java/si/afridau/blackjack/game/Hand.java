package si.afridau.blackjack.game;

import si.afridau.blackjack.core.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Hand(List<Card> cards, int[] handValue) {
    public Hand(List<Card> cards, int[] handValue) {
        this.cards = new ArrayList<>(cards);
        this.handValue = Arrays.copyOf(handValue, handValue.length);
    }
}

package si.afridau.blackjack.participant;

import si.afridau.blackjack.core.Card;

import java.util.List;

public interface ICardHolderInfo {
    int[] getHandValue();
    List<Card> getCards();
}

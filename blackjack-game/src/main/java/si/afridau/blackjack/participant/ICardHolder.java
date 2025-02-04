package si.afridau.blackjack.participant;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.core.Card;

import java.util.List;
import java.util.UUID;

public interface ICardHolder {
    int[] getHandValue();
    void receiveCard(Card card);
    void discardCards();
    List<Card> getCards();
    //decide method is here because the card holder can be a player or a dealer
    BetDecision decide();
    UUID getId();
}

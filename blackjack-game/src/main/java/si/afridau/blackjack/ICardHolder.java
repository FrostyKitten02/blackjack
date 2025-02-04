package si.afridau.blackjack;

import java.util.List;

public interface ICardHolder {
    int[] getHandValue();
    void receiveCard(Card card);
    void discardCards();
    List<Card> getCards();
    //decide method is here because the card holder can be a player or a dealer
    BetDecision decide();
}

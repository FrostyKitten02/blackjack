package si.afridau.blackjack.participant;

import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.participant.ICardHolder;

public interface IDealer extends ICardHolder {
    void shuffle();
    Card deal(ICardHolder recipient);
}

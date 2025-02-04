package si.afridau.blackjack.participant;

import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.game.listeners.ICardListener;
import si.afridau.blackjack.participant.ICardHolder;

public interface IDealer extends ICardHolder {
    void shuffle();
    Card deal(ICardHolder recipient);
    void addCardListener(ICardListener listener);
    //TODO add remove listener method
}

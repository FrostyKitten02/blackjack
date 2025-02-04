package si.afridau.blackjack.strategy;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.game.listeners.ICardListener;
import si.afridau.blackjack.participant.IPlayer;

public interface IStrategy extends ICardListener {
    BetDecision getDecision(IPlayer player);
    //add increase bet size?? or get bet size??
}

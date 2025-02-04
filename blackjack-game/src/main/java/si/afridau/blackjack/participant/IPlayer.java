package si.afridau.blackjack.participant;

import si.afridau.blackjack.participant.ICardHolder;

public interface IPlayer extends ICardHolder {
    float geBalance();
    float betAmount();
}

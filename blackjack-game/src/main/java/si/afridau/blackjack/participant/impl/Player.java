package si.afridau.blackjack.participant.impl;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.participant.CardHolder;
import si.afridau.blackjack.participant.IPlayer;

public class Player extends CardHolder implements IPlayer {
    private float balance = 0;

    @Override
    public float geBalance() {
        return balance;
    }

    @Override
    public BetDecision decide() {
        //TODO implement!!
        return BetDecision.STAND;
    }

    //TODO should dealer keep track of balance??
    @Override
    public float betAmount() {
        balance -= 10;
        return 10;
    }
}

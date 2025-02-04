package si.afridau.blackjack.participant.impl;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.participant.CardHolder;
import si.afridau.blackjack.participant.IPlayer;

public class Player extends CardHolder implements IPlayer {
    private float balance = 0;
    private float roundBetAmount = 0;

    public Player() {
        super();
    }

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
    public float getRoundBetAmount() {
        return this.roundBetAmount;
    }

    @Override
    public void placeRoundBetAmount() {
        //flat bet for now
        roundBetAmount = 10;
        balance -= roundBetAmount;
    }

    @Override
    public void rewardPlayer(float amount) {
       balance += amount;
    }
}

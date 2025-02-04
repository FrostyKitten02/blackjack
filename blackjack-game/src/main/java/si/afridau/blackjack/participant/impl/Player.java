package si.afridau.blackjack.participant.impl;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.participant.CardHolder;
import si.afridau.blackjack.participant.IPlayer;
import si.afridau.blackjack.strategy.IStrategy;

public class Player extends CardHolder implements IPlayer {
    private float balance = 0;
    private float roundBetAmount = 0;
    private final IStrategy strategy;

    public Player(IStrategy strategy) {
        super();
        this.strategy = strategy;
    }

    @Override
    public float geBalance() {
        return balance;
    }

    @Override
    public BetDecision decide() {
        //TODO implement!!
        return strategy.getDecision(this);
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

    @Override
    public IStrategy getStrategy() {
        return this.strategy;
    }
}

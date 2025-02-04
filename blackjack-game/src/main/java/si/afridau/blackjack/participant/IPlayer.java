package si.afridau.blackjack.participant;

import si.afridau.blackjack.strategy.IStrategy;

public interface IPlayer extends ICardHolder {
    float geBalance();
    float getRoundBetAmount();
    void placeRoundBetAmount();
    void rewardPlayer(float amount);
    //TODO: maybe move ti ICardHolder, so we can modify dealers strategy too
    IStrategy getStrategy();
}

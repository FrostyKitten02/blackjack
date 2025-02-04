package si.afridau.blackjack.participant;

public interface IPlayer extends ICardHolder {
    float geBalance();
    float getRoundBetAmount();
    void placeRoundBetAmount();
    void rewardPlayer(float amount);
}

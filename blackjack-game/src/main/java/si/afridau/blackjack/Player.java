package si.afridau.blackjack;

public class Player extends CardHolder implements IPlayer {
    private float balance = 0;

    @Override
    public float geBalance() {
        return balance;
    }

    @Override
    public BetDecision decide() {
        //TODO implement!!
        System.out.println("Player decides to stand");
        return BetDecision.STAND;
    }

    //TODO should dealer keep track of balance??
    @Override
    public float betAmount() {
        balance -= 10;
        return 10;
    }
}

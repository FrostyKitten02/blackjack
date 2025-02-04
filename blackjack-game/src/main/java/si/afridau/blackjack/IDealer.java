package si.afridau.blackjack;

public interface IDealer extends ICardHolder {
    void shuffle();
    Card deal(ICardHolder recipient);
}

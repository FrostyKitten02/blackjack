package si.afridau.blackjack;


import si.afridau.blackjack.game.BlackJack;
import si.afridau.blackjack.participant.impl.Dealer;
import si.afridau.blackjack.participant.impl.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer(3);
        BlackJack game = new BlackJack(player, dealer);
        game.start();
        game.playRound();
        game.playRound();
        game.playRound();
    }
}
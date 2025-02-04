package si.afridau.blackjack;


import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer(3);
        BlackJack game = new BlackJack(player, dealer);
        game.playRound();
        game.playRound();
        game.playRound();
    }
}
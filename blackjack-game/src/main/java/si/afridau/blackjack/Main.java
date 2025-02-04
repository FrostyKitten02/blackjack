package si.afridau.blackjack;


import si.afridau.blackjack.game.BlackJack;
import si.afridau.blackjack.game.RoundResult;
import si.afridau.blackjack.participant.impl.Dealer;
import si.afridau.blackjack.participant.impl.Player;
import si.afridau.blackjack.strategy.SimpleStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int DECKS = 3;

        Dealer dealer = new Dealer(DECKS);
        SimpleStrategy strategy = new SimpleStrategy(DeckUtil.getTotalCards(DECKS), dealer);
        Player player = new Player(strategy);
        BlackJack game = new BlackJack(player, dealer);

        game.start();

        List<RoundResult> roundResults = new ArrayList<>(10);
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());
        roundResults.add(game.playRound());



        System.out.println("Game over, results:");
    }
}
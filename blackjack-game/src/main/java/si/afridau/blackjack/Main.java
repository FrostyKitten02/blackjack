package si.afridau.blackjack;


import si.afridau.blackjack.game.BlackJack;
import si.afridau.blackjack.game.PlayerResult;
import si.afridau.blackjack.game.RoundResult;
import si.afridau.blackjack.participant.impl.Dealer;
import si.afridau.blackjack.participant.impl.Player;
import si.afridau.blackjack.strategy.SimpleStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        final int DECKS = 6;
        final int ROUNDS = 60;

        Dealer dealer = new Dealer(DECKS);
        SimpleStrategy strategy = new SimpleStrategy(DeckUtil.getTotalCards(DECKS), dealer);
        Player player = new Player(strategy);
        BlackJack game = new BlackJack(player, dealer);

        game.start();

        List<RoundResult> roundResults = new ArrayList<>(10);
        for (int i = 0; i < ROUNDS; i++) {
            roundResults.add(game.playRound());
        }


        Map<UUID, List<PlayerResult>> compiled = new HashMap<>();
        //min and max balance, not correct it is players wide and not per player
        float min = 0;
        float max = 0;
        for (RoundResult rr : roundResults) {
            for (Map.Entry<UUID, PlayerResult> entry : rr.getPlayerResults().entrySet()) {
                min = entry.getValue().balance();
                if (compiled.containsKey(entry.getKey())) {
                    compiled.get(entry.getKey()).add(entry.getValue());
                } else {
                    List<PlayerResult> results = new ArrayList<>();
                    results.add(entry.getValue());
                    compiled.put(entry.getKey(), results);
                }

                if (entry.getValue().balance() < min) {
                    min = entry.getValue().balance();
                }

                if (entry.getValue().balance() > max) {
                    max = entry.getValue().balance();
                }
            }
        }


        System.out.println("Game over, results:");
    }
}
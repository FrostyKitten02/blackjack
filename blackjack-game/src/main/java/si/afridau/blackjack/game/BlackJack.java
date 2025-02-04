package si.afridau.blackjack.game;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.participant.ICardHolder;
import si.afridau.blackjack.participant.IDealer;
import si.afridau.blackjack.participant.IPlayer;

import java.util.UUID;

public class BlackJack {
    private final IPlayer player;
    private final IDealer dealer;
    private final UUID gameId;

    private GameState state = GameState.NOT_READY;

    public BlackJack(IPlayer player, IDealer dealer) {
        this.player = player;
        this.dealer = dealer;
        this.gameId = UUID.randomUUID();
    }

    public void start() {
        dealer.shuffle();
        state = GameState.STARTED;
    }

    public void playRound() {
        if (state == GameState.NOT_READY) {
            throw new IllegalStateException("Game not started yet");
        }

        if (state == GameState.ROUND_IN_PROGRESS) {
            throw new IllegalStateException("Game already in progress");
        }

        state = GameState.ROUND_IN_PROGRESS;
        UUID roundId = UUID.randomUUID();
        //TODO: implement reward system!!
        float bet = player.betAmount();
        //first hand
        dealer.deal(player);
        dealer.deal(dealer);
        dealer.deal(player);
        //TODO check for blackjacks!!
        //wait for player action
        BetDecision decision = player.decide();

        //AFTER one player is finished we would move to another but now its 1 on 1 so we move to dealer
        int[] playerValue = cardHolderMove(player, decision);
        if (playerValue[0] > 21) {
            System.out.println("Player busted with value: " + playerValue[0]);
            //with multiple players we would continue to the next player
            player.discardCards();
            return;
        }

        //at this point player has either busted or stood or cannot play that hand anymore, for example if he doubled
        dealer.deal(dealer); //dealer's second card, after that he makes a decision
        int[] dealerValue = cardHolderMove(dealer, dealer.decide());
        if (dealerValue[0] > 21) {
            System.out.println("Dealer busted with value: " + dealerValue[0] + " vs player value: " + playerValue[0]);
            dealer.discardCards();
            return;
        }

        if (dealerValue[0] > playerValue[0]) {
            System.out.println("Dealer wins with value: " + dealerValue[0] + " vs player value: " + playerValue[0]);
        } else if (dealerValue[0] < playerValue[0]) {
            System.out.println("Player wins with value: " + playerValue[0] + " vs dealer value: " + dealerValue[0]);
        } else {
            System.out.println("It's a tie with value of: " + playerValue[0]);
        }

        player.discardCards();
        dealer.discardCards();

        state = GameState.STARTED;
    }

    //RETURNS player's hand value
    public int[] cardHolderMove(ICardHolder player, BetDecision decision) {
        switch (decision) {
            case HIT -> {
                dealer.deal(player);
                int[] handValue = player.getHandValue();
                if (handValue[0] > 21) {
                    return handValue;
                }

                //if player hit and didn't bust, then we ask for another decision until player stands or busts
                BetDecision newDecision = player.decide();
                if (newDecision == BetDecision.SPLIT || newDecision == BetDecision.DOUBLE) {
                    throw new UnsupportedOperationException("Cannot split or double after hitting");
                }

                return cardHolderMove(player, newDecision);
            }
            case STAND -> {
                return player.getHandValue();
            }
            case DOUBLE -> throw new UnsupportedOperationException("Not implemented yet");
            case SPLIT -> throw new UnsupportedOperationException("Not implemented yet");
        };

        throw new UnsupportedOperationException("Unexpected error when executing move for player: Invalid bet decision");
    }

}

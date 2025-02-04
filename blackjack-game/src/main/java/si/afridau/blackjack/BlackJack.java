package si.afridau.blackjack;

public class BlackJack {
    private IPlayer player;
    private IDealer dealer;

    public BlackJack(IPlayer player, IDealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public void playRound() {
        System.out.println("Game started");
        dealer.shuffle();

        //TODO: implement reward system!!
        float bet = player.betAmount();
        System.out.println("Player bet: " + bet);
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

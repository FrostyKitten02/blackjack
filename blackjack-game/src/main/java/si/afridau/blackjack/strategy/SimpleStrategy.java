package si.afridau.blackjack.strategy;

import si.afridau.blackjack.DeckUtil;
import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.participant.ICardHolderInfo;
import si.afridau.blackjack.participant.IPlayer;

public class SimpleStrategy implements IStrategy {
    private int count = 0;
    private float trueCount = 0;
    private final int totalCards;
    private int cardsDealt = 0;
    private static final Card[] lowCards = {Card.TWO, Card.THREE, Card.FOUR, Card.FIVE, Card.SIX};
    private final ICardHolderInfo dealer;

    public SimpleStrategy(int totalCards, ICardHolderInfo dealer) {
        this.totalCards = totalCards;
        this.dealer = dealer;
    }

    @Override
    public void cardDealt(Card card) {
        cardsDealt++;
        if (card.value > 9) {
            count--;
        }

        if (isLowCard(card)) {
            count++;
        }

        trueCount = calculateTrueCount();
    }

    private float calculateTrueCount() {
        float decksLeft = (float) (totalCards - cardsDealt) / DeckUtil.getDeckSize();
        return count / decksLeft;
    }

    private boolean isLowCard(Card card) {
        for (Card c : lowCards) {
            if (c == card) {
                return true;
            }
        }

        return false;
    }

    //TODO make some kind of condition builder based on player hand and dealer hand
    @Override
    public BetDecision getDecision(IPlayer player) {
        int[] cardVal = player.getHandValue();
        boolean isSoft = cardVal.length > 1;
        int maxVal = cardVal[0];


        //TODO use true count and dealer card to make decision
        if (isSoft) {
            if (maxVal > 15) {
                return BetDecision.HIT;
            }

            return BetDecision.STAND;
        }

        if (maxVal > 18) {
            return BetDecision.STAND;
        }

        if (maxVal < 11) {
            return BetDecision.HIT;
        }


        return BetDecision.STAND;
    }
}

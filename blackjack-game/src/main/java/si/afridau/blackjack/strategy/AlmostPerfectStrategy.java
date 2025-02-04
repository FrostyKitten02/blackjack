package si.afridau.blackjack.strategy;

import si.afridau.blackjack.DeckUtil;
import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.participant.ICardHolderInfo;
import si.afridau.blackjack.participant.IPlayer;

public class AlmostPerfectStrategy implements IStrategy {
    private int count = 0;
    private float trueCount = 0;
    private final int totalCards;
    private int cardsDealt = 0;
    private static final Card[] lowCards = {Card.TWO, Card.THREE, Card.FOUR, Card.FIVE, Card.SIX};
    private final ICardHolderInfo dealer;

    public AlmostPerfectStrategy(int totalCards, ICardHolderInfo dealer) {
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
        int dealerVal = dealer.getHandValue()[0];
        int maxVal = cardVal[0];

        if (isSoft) {
            boolean hasAce = player.getCards().contains(Card.ACE);
            boolean hasNine = player.getCards().contains(Card.NINE);
            boolean hasEight = player.getCards().contains(Card.NINE);

            if (hasAce && (hasEight ||hasNine)) {
                return BetDecision.STAND;
            }

            boolean hasSeven = player.getCards().contains(Card.SEVEN);

            if (hasAce && hasSeven && dealerVal <= 6) {
                return BetDecision.HIT; //should double
            }

            //on dealer 7 and 8 we stand
            if (hasAce && hasSeven && dealerVal <= 8) {
                return BetDecision.STAND;
            }


            if (hasAce) {
                return BetDecision.HIT;
            }
        }

        if (maxVal == 17) {
            return BetDecision.STAND;
        }

        if (dealerVal <= 6 && maxVal >= 12) {
            return BetDecision.STAND;
        }

        if (dealerVal >= 7 && maxVal >= 12) {
            return BetDecision.HIT;
        }

        //TODO: think about doubling
        return BetDecision.HIT;
    }
}

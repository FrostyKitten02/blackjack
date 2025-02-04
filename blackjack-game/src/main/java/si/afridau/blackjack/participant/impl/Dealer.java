package si.afridau.blackjack.participant.impl;

import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.participant.CardHolder;
import si.afridau.blackjack.participant.ICardHolder;
import si.afridau.blackjack.participant.IDealer;
import si.afridau.blackjack.participant.IPlayer;

import java.util.Random;

public class Dealer extends CardHolder implements IDealer {
    private final Card[] cards;
    private int currentCardIndex = 0;

    public Dealer(int numberOfDecks) {
        super();
        final int deckLen = Card.deck.length;
        cards = new Card[deckLen * numberOfDecks];

        int destPos = 0;
        for (int i = 0; i < numberOfDecks; i++) {
            System.arraycopy(Card.deck, 0, cards, destPos, deckLen);
            destPos += deckLen;
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int j = random.nextInt(cards.length);

            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card deal(ICardHolder recipient) {
        //TODO maybe set dealt card to -1, just in case?
        Card card = cards[currentCardIndex];
        recipient.receiveCard(card);
        currentCardIndex++;
        return card;
    }

    @Override
    public BetDecision decide() {
        if (this.getHandValue()[0] < 17) {
            return BetDecision.HIT;
        }

        return BetDecision.STAND;
    }
}

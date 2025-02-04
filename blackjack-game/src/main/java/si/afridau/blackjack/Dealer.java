package si.afridau.blackjack;

import java.util.Random;

public class Dealer extends CardHolder implements IDealer {
    private final Card[] cards;
    private int currentCardIndex = 0;

    public Dealer(int numberOfDecks) {
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

package si.afridau.blackjack.participant.impl;

import si.afridau.blackjack.DeckUtil;
import si.afridau.blackjack.core.BetDecision;
import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.game.listeners.ICardListener;
import si.afridau.blackjack.participant.CardHolder;
import si.afridau.blackjack.participant.ICardHolder;
import si.afridau.blackjack.participant.IDealer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer extends CardHolder implements IDealer {
    private final Card[] cards;
    private int currentCardIndex = 0;
    private int numberOfDecks;
    private final List<ICardListener> cardListeners = new ArrayList<>();

    public Dealer(int numberOfDecks) {
        super();
        this.numberOfDecks = numberOfDecks;
        this.cards = new Card[DeckUtil.getTotalCards(numberOfDecks)];
        setCards();
    }

    private void setCards() {
        final int deckLen = DeckUtil.getDeckSize();
        int destPos = 0;
        for (int i = 0; i < numberOfDecks; i++) {
            System.arraycopy(Card.deck, 0, cards, destPos, deckLen);
            destPos += deckLen;
        }
    }

    @Override
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.length * 2; i++) {
            int j = random.nextInt(cards.length);
            int k = random.nextInt(cards.length);

            Card temp = cards[k];
            cards[k] = cards[j];
            cards[j] = temp;
        }
    }

    //TODO we don't need to return card?
    @Override
    public Card deal(ICardHolder recipient) {
        if (currentCardIndex >= cards.length - 1) {
            setCards();
            shuffle();
            currentCardIndex = 0;
        }

        //TODO maybe set dealt card to -1, just in case?
        Card card = cards[currentCardIndex];
        recipient.receiveCard(card);
        currentCardIndex++;
        notifyCardListeners(card);
        return card;
    }

    @Override
    public BetDecision decide() {
        if (this.getHandValue()[0] < 17) {
            return BetDecision.HIT;
        }

        return BetDecision.STAND;
    }

    @Override
    public void addCardListener(ICardListener listener) {
        cardListeners.add(listener);
    }

    private void notifyCardListeners(Card card) {
        for (ICardListener listener : cardListeners) {
            listener.cardDealt(card);
        }
    }
}

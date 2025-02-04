package si.afridau.blackjack.participant;

import si.afridau.blackjack.core.Card;
import si.afridau.blackjack.HandUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class CardHolder implements ICardHolder {
    private List<Card> cards = new ArrayList<>(10);
    private int[] currentHandValue = new int[]{0,0};
    private final UUID id;

    public CardHolder() {
        this.id = UUID.randomUUID();
    }

    @Override
    public int[] getHandValue() {
        return this.currentHandValue;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
        currentHandValue = HandUtil.handValue(cards);
    }

    @Override
    public void discardCards() {
        currentHandValue = new int[]{0,0};
        cards.clear();
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public UUID getId() {
        return id;
    }
}

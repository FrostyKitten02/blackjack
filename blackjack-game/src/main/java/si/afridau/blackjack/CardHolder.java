package si.afridau.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CardHolder implements ICardHolder {
    private List<Card> cards = new ArrayList<>(10);
    private int[] currentHandValue = new int[]{0,0};

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
}

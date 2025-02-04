package si.afridau.blackjack;

import si.afridau.blackjack.core.Card;

import java.util.List;

public class HandUtil {
    private HandUtil() {}

    private static final int ACE_MIN_MAX_VALUE_DIFF = Card.ACE.value - Card.LOW_ACE_VALUE;
    public static int[] handValue(List<Card> cards) {
        boolean maxAceValApplied = false;
        int value = 0;
        for (Card c : cards) {
            if (c == Card.ACE) {
                maxAceValApplied = true;
                continue;
            }

            value += c.value;
            if (value > 21 && maxAceValApplied) {
                value -= ACE_MIN_MAX_VALUE_DIFF;
                maxAceValApplied = false;
            }
        }

        if (maxAceValApplied) {
            return new int[] {value, value - ACE_MIN_MAX_VALUE_DIFF};
        }

        return new int[] {value};
    }


}

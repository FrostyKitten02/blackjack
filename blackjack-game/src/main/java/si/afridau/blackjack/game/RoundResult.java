package si.afridau.blackjack.game;

import si.afridau.blackjack.participant.IDealer;
import si.afridau.blackjack.participant.IPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RoundResult {
    private final Map<UUID, PlayerResult> playerResults;
    private final Hand dealerHand;
    private final UUID roundId;

    public RoundResult(List<IPlayer> players, IDealer dealer, UUID roundId) {
        this.roundId = roundId;
        this.playerResults = new HashMap<>(players.size());
        this.dealerHand = new Hand(dealer.getCards(), dealer.getHandValue());

        for (IPlayer player : players) {
            Hand playerHand = new Hand(player.getCards(), player.getHandValue());
            PlayerResult playerResult = new PlayerResult(playerHand, player.geBalance());
            this.playerResults.put(player.getId(), playerResult);
        }
    }

    public Map<UUID, PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public UUID getRoundId() {
        return roundId;
    }
}

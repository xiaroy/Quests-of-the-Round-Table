package controller.AI;

import java.util.ArrayList;

import model.cards.AdventureCard;
import model.cards.Card;
import model.cards.Card.CardTypes;
import model.game.GameState;
import model.player.Player;

public class AIBidStrategy2 implements AIBidStrategy {

	@Override
	public int NumberOfCardsToBid(GameState state, Player player) {
		int counter = 0;
        for (AdventureCard card : player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe && card.getBattlePoints(state) <= 25)
            	counter++;
        }

        return counter;
	}

	@Override
	public Card[] CardsToDiscard(GameState state, Player player) {
		ArrayList<Card> remove = new ArrayList<>();
        for (AdventureCard card : player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe && card.getBattlePoints(state) <= 25)
                remove.add(card);
        }

        return remove.toArray(new Card[remove.size()]);
	}

}

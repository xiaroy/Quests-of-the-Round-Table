package controller.AI;

import model.cards.Card;
import model.game.GameState;
import model.player.Player;

public interface AIBidStrategy {
	int NumberOfCardsToBid(GameState state, Player player);
	Card[] CardsToDiscard(GameState state, Player player);
}

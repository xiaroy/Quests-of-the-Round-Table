package controller.AI;

import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;

public interface AISponsorStrategy {
	boolean DoISponorQuest(GameState state, Player player);
    Ability[] CardsToPlay(GameState state, Player player);
}

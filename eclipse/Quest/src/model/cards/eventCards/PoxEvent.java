package model.cards.eventCards;

import model.cards.EventCard;
import model.game.GameState;
import model.player.Player;

public class PoxEvent extends EventCard {

	public PoxEvent() {
		super("Pox");
	}

	@Override
	public void doEffect(GameState gState) {
		for (Player p : gState.getPlayers()) {
			if (p != gState.getCurrentTurnPlayer())
				p.AddShields(-1);
		}
	}

}

package model.cards.eventCards;

import model.cards.EventCard;
import model.game.GameState;

public class PlagueEvent extends EventCard {

	public PlagueEvent() {
		super("Plague");
	}

	@Override
	public void doEffect(GameState gState) {
		gState.getCurrentTurnPlayer().AddShields(-2);
	}

}

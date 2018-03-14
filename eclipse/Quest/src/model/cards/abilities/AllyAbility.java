package model.cards.abilities;

import model.cards.AdventureCard;
import model.game.GameState;
import model.game.GameState.GameTime;
import model.player.Player;

public class AllyAbility extends Ability {

	public AllyAbility(AdventureCard sourceCard) {
		super("Play Ally", sourceCard);
	}
	
	@Override
	public boolean CanUseAbility(GameState gState, Player sourcePlayer) {
		if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest)
            return true;
        return false;
	}

	@Override
	public void UseAbility(GameState gState, Player sourcePlayer) {
		if (CanUseAbility(gState, sourcePlayer))
        {
            sourcePlayer.PlayCardFromHandToBoard(this.getSourceCard());
        }
	}

}

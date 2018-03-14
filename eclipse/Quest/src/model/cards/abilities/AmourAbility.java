package model.cards.abilities;

import model.cards.AdventureCard;
import model.game.GameState;
import model.game.GameState.GameTime;
import model.player.Player;

public class AmourAbility extends Ability {

	public AmourAbility(AdventureCard sourceCard) {
		super("Play Amour", sourceCard);
	}
	
	@Override
	public boolean CanUseAbility(GameState gState, Player sourcePlayer) {
		if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest ||
				gState.getCurrentGameTime() == GameTime.SelectCardsForTournament)
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

package model.cards.abilities;

import model.cards.AdventureCard;
import model.game.GameState;
import model.game.GameState.GameTime;
import model.game.QuestInfo;
import model.game.QuestStage;
import model.player.Player;

public class FoeAbility extends Ability {

	private QuestStage target;

    public FoeAbility(AdventureCard sourceCard) {
    	super("Play Foe", sourceCard);
    }
    
    public boolean DoesTarget(GameState state) { return true; }
	public boolean CanTarget(GameState state, Object obj) { return obj instanceof QuestStage; }
    public void SetTarget(GameState state, Object obj) { 
    	if (CanTarget(state, obj))
    		target = (QuestStage)obj;
    }

    public boolean CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectQuestEnemies)
            return true;
        return false;
    }

    public void UseAbility(GameState gState, Player sourcePlayer)
    {
        if (CanUseAbility(gState, sourcePlayer))
        {
        	if (sourcePlayer.RemoveCardFromHand(this.getSourceCard()))
        		target.addCard(this.getSourceCard());
        }
    }
    
}

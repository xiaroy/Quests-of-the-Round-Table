package model.cards.abilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.cards.AdventureCard;
import model.game.GameState;
import model.game.GameState.GameTime;
import model.game.QuestStage;
import model.player.Player;

public class WeaponAbility extends Ability {

	private QuestStage target = null;
	
	public WeaponAbility(AdventureCard sourceCard) {
		super("Play Weapon", sourceCard);
	}
	
	public boolean DoesTarget(GameState state) { return state.getCurrentGameTime() == GameTime.SelectQuestEnemies; }
	public boolean CanTarget(GameState state, Object obj) { return (state.getCurrentGameTime() == GameTime.SelectQuestEnemies && obj instanceof QuestStage); }
    public void SetTarget(GameState state, Object obj) { 
    	if (CanTarget(state, obj))
    		target = (QuestStage)obj;
    }

    public boolean CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest ||
            gState.getCurrentGameTime() == GameTime.SelectCardsForTournament ||
            gState.getCurrentGameTime() == GameTime.SelectQuestEnemies)
            return true;
        return false;
    }

    public void UseAbility(GameState gState, Player sourcePlayer)
    {
        if (CanUseAbility(gState, sourcePlayer))
        {
            if (gState.getCurrentGameTime() == GameTime.SelectQuestEnemies) {
            	if (sourcePlayer.RemoveCardFromHand(this.getSourceCard())) {
            		target.addCard(this.getSourceCard());
            		Logger.getLogger(WeaponAbility.class.getName()).log(Level.FINE, sourcePlayer.GetName() + " played " + this.getSourceCard().getName() + " to stage " + target.getStageNum());
            	}
            }
            else {
            	sourcePlayer.PlayCardFromHandToBoard(this.getSourceCard());
            	Logger.getLogger(WeaponAbility.class.getName()).log(Level.FINE, sourcePlayer.GetName() + " played " + this.getSourceCard().getName());
            }
        }
    }
}

using UnityEngine;
using System.Collections;

public class AmourAbility : Ability
{

    public AmourAbility(AdventureCard sourceCard): base("Play Amour", sourceCard) { }
    
    public override bool CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest ||
                gState.getCurrentGameTime() == GameTime.SelectCardsForTournament)
            return true;
        return false;
    }
    
    public override void UseAbility(GameState gState, Player sourcePlayer)
    {
        if (CanUseAbility(gState, sourcePlayer))
        {
            sourcePlayer.PlayCardFromHandToBoard(this.getSourceCard());
        }
    }
}

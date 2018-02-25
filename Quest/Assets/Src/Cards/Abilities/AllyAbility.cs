using UnityEngine;
using System.Collections;

public class AllyAbility : Ability
{

    public AllyAbility(AdventureCard sourceCard) : base("Play Ally", sourceCard) { }
    
    public override bool CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest)
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

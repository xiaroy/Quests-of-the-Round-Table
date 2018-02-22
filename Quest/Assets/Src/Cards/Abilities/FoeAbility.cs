using UnityEngine;
using System.Collections;

public class FoeAbility : Ability
{

    private QuestInfo target;

    public FoeAbility(AdventureCard sourceCard) : base("PlayFoe", sourceCard) { }

    public override bool CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectQuestEnemies)
            return true;
        return false;
    }

    public override void UseAbility(GameState gState, Player sourcePlayer)
    {
        if (CanUseAbility(gState, sourcePlayer))
        {
            gState.AddCardToQuestInfo(this.getSourceCard());
        }
    }
}

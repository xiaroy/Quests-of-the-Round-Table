using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WeaponAbility : Ability
{

    public WeaponAbility(AdventureCard sourceCard) : base("PlayWeapon", sourceCard) { }

    public override bool CanUseAbility(GameState gState, Player sourcePlayer)
    {
        if (gState.getCurrentGameTime() == GameTime.SelectCardsForQuest ||
            gState.getCurrentGameTime() == GameTime.SelectCardsForTournament ||
            gState.getCurrentGameTime() == GameTime.SelectQuestEnemies)
            return true;
        return false;
    }

    public override void UseAbility(GameState gState, Player sourcePlayer)
    {
        if (CanUseAbility(gState, sourcePlayer))
        {
            if (gState.getCurrentGameTime() == GameTime.SelectQuestEnemies)
                gState.AddCardToQuestInfo(this.getSourceCard());
            else sourcePlayer.PlayCardFromHandToBoard(this.getSourceCard());
        }
    }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WeaponAbility : Ability
{
    private QuestStage target = null;

    public WeaponAbility(AdventureCard sourceCard) : base("PlayWeapon", sourceCard) { }

    public override bool DoesTarget(GameState state) { return state.getCurrentGameTime() == GameTime.SelectQuestEnemies; }
    public override bool CanTarget(GameState state, Targetable obj) { return (state.getCurrentGameTime() == GameTime.SelectQuestEnemies && obj.GetType() == typeof(QuestStage)); }
    public override void SetTarget(GameState state, Targetable obj)
    {
        if (CanTarget(state, obj))
            target = (QuestStage)obj;
    }

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
            {
                if (sourcePlayer.RemoveCardFromHand(this.getSourceCard()))
                    target.addCard(this.getSourceCard());
            }
            else sourcePlayer.PlayCardFromHandToBoard(this.getSourceCard());
        }
    }
}

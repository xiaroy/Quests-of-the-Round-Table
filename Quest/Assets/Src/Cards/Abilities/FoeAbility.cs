﻿using UnityEngine;
using System.Collections;

public class FoeAbility : Ability
{

    private QuestStage target;

    public FoeAbility(AdventureCard sourceCard) : base("PlayFoe", sourceCard) { }

    public override bool DoesTarget(GameState state) { return true; }
    public override bool CanTarget(GameState state, Targetable obj) { return obj.GetType() == typeof(QuestStage); }
    public override void SetTarget(GameState state, Targetable obj)
    {
        if (CanTarget(state, obj))
            target = (QuestStage)obj;
    }

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
            if (sourcePlayer.RemoveCardFromHand(this.getSourceCard()))
                target.addCard(this.getSourceCard());
        }
    }
}

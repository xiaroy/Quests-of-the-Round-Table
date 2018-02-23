using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SirGawain : AllyCard
{

    public SirGawain() : base("Sir Gawain", 10, 1) //base 10
    {

    }

    public override int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().Equals("Test of the Green Knight"))
        {
            return battlePoints = 20;
        }
        return battlePoints = 10;
    }

    public override Ability[] GetAbilities() //no ability
    {
        return null;
    }

}

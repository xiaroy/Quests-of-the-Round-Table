using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SirLancelot : AllyCard
{

    public SirLancelot() : base("Sir Lancelot", 15, 1) //base 5
    {

    }

    public override int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().Equals("Defend the Queen's Honor"))
        {
            return battlePoints = 25;
        }
        return battlePoints = 15;
    }
    

}

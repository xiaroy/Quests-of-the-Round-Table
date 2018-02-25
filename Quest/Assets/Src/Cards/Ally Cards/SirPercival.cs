using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SirPercival : AllyCard
{

    public SirPercival() : base("Sir Percival", 5, 1) //base 5
    {

    }

    public override int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().Equals("Search for the Holy Grail"))
        {
            return battlePoints = 20;
        }
        return battlePoints = 5;
    }

}

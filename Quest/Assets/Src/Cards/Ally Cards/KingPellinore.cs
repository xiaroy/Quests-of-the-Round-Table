using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KingPellinore : AllyCard
{

    public KingPellinore() : base("King Pellinore", 10, 1) //base 10
    {

    }

    public override int getBidPower(GameState gState) //override 5
    {
        if (gState.getCurrentStoryCard().getName().Equals("Search for the Question Beast"))
        {
            return bidPower = 5;
        }
        return bidPower = 1;
    }

}

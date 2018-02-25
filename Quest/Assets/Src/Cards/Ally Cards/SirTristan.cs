using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SirTristan : AllyCard
{

    public SirTristan() : base("Sir Tristan", 10, 1) //base 10
    {

    }

    public override int getBattlePoints(GameState gState) //override 20
    {
        //Player[] players = gState.getPlayers();
        foreach (Player player in gState.getPlayers())
        {
            if (player.getPlayersBoard().Equals("Queen Iseult"))
            {
                return battlePoints = 20;
            }
            return battlePoints = 10;
        }
        return battlePoints = 10;
    }

}
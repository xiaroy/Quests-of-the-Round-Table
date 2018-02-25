using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QueenIseult : AllyCard
{

    public QueenIseult() : base("Queen Iseult", 0, 2) //base 10
    {

    }

    public override int getBattlePoints(GameState gState) //override 20
    {
        //Player[] players = gState.getPlayers();
        foreach (Player player in gState.getPlayers())
        {
            if (player.getPlayersBoard().Equals("Sir Tristan"))
            {
                return bidPower = 4;
            }
            return bidPower = 2;
        }
        return bidPower = 2;
    }

}
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KingsRecognition : EventCard
{
    public KingsRecognition() : base("King's Recognition")
    {

    }

    public override void doEffect(GameState gState)
    {
        Player[] players = gState.getPlayersInRankOrder();
        Player[] completequest = new Player[4];
        int i = 0;

        foreach (Player player in gState.getPlayers())
        {
            if (player.GetRank() == players[players.Length - 1].GetRank())
            {
               // lowest[i] = players[players.Length - 1];
                i = i + 1;
            }
        }
    }
}

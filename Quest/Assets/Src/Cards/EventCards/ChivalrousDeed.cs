using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChivalrousDeed : EventCard
{
    public ChivalrousDeed() : base("Chivalrous Deed") { }

    public override void doEffect(GameState gState)
    {
        //initialization;
        Player[] players = gState.getPlayersInRankOrder();
        Player[] lowest = new Player[4];
        int i = 1;
        lowest[0] = players[players.Length-1];

        //find lowest and put in lowest
        foreach (Player player in gState.getPlayers())
        {
            if(player.GetRank() == players[players.Length - 1].GetRank())
            {
                lowest[i] = players[players.Length - 1];
                i = i + 1;
            }
        }

        //add three shields for lowest
        foreach (Player p in lowest)
        {
            p.AddShields(3);
        }
    }
}

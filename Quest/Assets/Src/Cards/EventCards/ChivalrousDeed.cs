using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChivalrousDeed : EventCard
{
    public ChivalrousDeed() : base("Chivalrous Deed") { }

    public override void doEffect(GameState gState)
    {
        Player[] players = gState.getPlayersInRankOrder();
        Player[] lowest = gState.getPlayers();
        int i = 1;
        lowest[0] = players[0];

        foreach (Player player in gState.getPlayers())
        {
            if(player.GetRank() == players[players.Length - 1].GetRank())
            {
                lowest[i] = players[players.Length - 1];
                i = i + 1;
            }
        }

        foreach (Player p in lowest)
        {
            p.addShields(3);
        }
    }
}

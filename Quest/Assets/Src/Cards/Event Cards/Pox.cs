using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pox : EventCard {

    public Pox() : base("Pox") {

    }

    public override void doEffect(GameState gState)
    {
        Player[] players = gState.getPlayersInRankOrder();
        Player[] poxplayers = new Player[4];
        int i = 0;

        foreach (Player player in gState.getPlayers())
        {
            if (player == gState.getCurrentTurnPlayer())
            {
                return;
            }
            else
            {
                poxplayers[i] = players[players.Length - 1];
                i = i + 1;
            }
        }

        foreach (Player p in poxplayers)
        {
            p.AddShields(-1);
        }
    }

}

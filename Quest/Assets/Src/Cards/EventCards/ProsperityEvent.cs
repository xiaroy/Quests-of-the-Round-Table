using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ProsperityEvent : EventCard
{

    public ProsperityEvent() : base("Prosperity Throughout the Kingdom") { }

    public override void doEffect(GameState gState)
    {
        //Each player draws 2 cards
        Player[] players = gState.getPlayers();
        foreach (Player player in gState.getPlayers())
        {
            for (int i = 0; i < 2; i++)
                player.addCard((AdventureCard)gState.getAdventureDeck().Draw());
        }
    }
}

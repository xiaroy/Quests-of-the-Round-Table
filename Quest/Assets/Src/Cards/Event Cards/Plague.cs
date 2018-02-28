using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Plague : EventCard {

    public Plague() : base("Plague")
    {

    }

    public override void doEffect(GameState gState)
    {
        Player[] plagueplayers = new Player[1];
        plagueplayers[0] = gState.getCurrentTurnPlayer();

        foreach (Player p in plagueplayers)
        {
            p.AddShields(-2);
        }
    }
}

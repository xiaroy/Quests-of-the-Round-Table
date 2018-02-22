using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KingsRecognition : EventCard
{

    public KingsRecognition() : base("King's Recognition") { }

    public override void doEffect(GameState gState)
    {
        //The next player(s) that complete a quest get 2 extra shields
    }
}

using UnityEngine;
using System.Collections;

public abstract class TestCard : AdventureCard
{

    public TestCard(string name) : base(name, CardTypes.Test, 0) { }

    public override bool hasEffect() { return true; }

    public override void doEffect(/*GameState gState*/)
    {
        /*
         * gState.startTest(this);
         */
    }
}

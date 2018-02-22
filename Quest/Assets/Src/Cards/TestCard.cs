using UnityEngine;
using System.Collections;

[CreateAssetMenu(fileName = "New Test Card", menuName = "Test Card")]
public class TestCard : AdventureCard
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

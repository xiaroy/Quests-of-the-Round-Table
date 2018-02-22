using UnityEngine;
using System.Collections;

public class AllyCard : AdventureCard
{
    public AllyCard(string name, int battlePoints) : base(name, CardTypes.Ally, battlePoints) { }

    public override void doEffect()
    {
        throw new System.NotImplementedException();
    }

    public override bool hasEffect()
    {
        throw new System.NotImplementedException();
    }
}

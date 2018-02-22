using UnityEngine;
using System.Collections;

[CreateAssetMenu(fileName = "New Ally Card", menuName = "Ally Card")]

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

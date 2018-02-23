using UnityEngine;
using System.Collections;

public class AmourCard : AdventureCard
{
    public AmourCard() : base("Amour", CardTypes.Amour, 10, 1) { }

    public override Ability[] GetAbilities()
    {
        return null;
    }
}

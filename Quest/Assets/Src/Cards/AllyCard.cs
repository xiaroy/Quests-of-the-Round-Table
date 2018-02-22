using UnityEngine;
using System.Collections;

public abstract class AllyCard : AdventureCard
{
    public AllyCard(string name, int battlePoints) : base(name, CardTypes.Ally, battlePoints) { }

}

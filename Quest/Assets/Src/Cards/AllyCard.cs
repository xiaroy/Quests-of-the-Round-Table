using UnityEngine;
using System.Collections;

public abstract class AllyCard : AdventureCard
{
    public AllyCard(string name, int battlePoints, int bidPower = 1) : base(name, CardTypes.Ally, battlePoints, bidPower) { }

}

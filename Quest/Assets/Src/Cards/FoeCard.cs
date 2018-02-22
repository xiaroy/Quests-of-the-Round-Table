using UnityEngine;
using System.Collections;



public abstract class FoeCard : AdventureCard
{
    protected int highBattlePoints;

    public FoeCard(string name, int lowBattlePoints, int highBattlePoints = -1) : base(name, CardTypes.Foe, lowBattlePoints)
    {
        this.highBattlePoints = highBattlePoints;
    }

}

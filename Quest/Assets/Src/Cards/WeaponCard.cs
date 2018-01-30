using UnityEngine;
using System.Collections;

public abstract class WeaponCard : AdventureCard
{

    public WeaponCard(string name, int battlePoints) : base(name, CardTypes.Weapon, battlePoints) { }

    public override bool hasEffect() { return false; }

    public override void doEffect(/*GameState gState*/) { }
}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KingArthur : AllyCard
{

    public KingArthur() : base("King Arthur", 10, 2) //base 5
    {

    }

    public override Ability[] GetAbilities() //no ability
    {
        return null;
    }

}

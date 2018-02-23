using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Amour : AllyCard
{

    public Amour() : base("Amour", 10, 1) //base 5
    {

    }
    public override Ability[] GetAbilities() //no ability
    {
        return null;
    }

}
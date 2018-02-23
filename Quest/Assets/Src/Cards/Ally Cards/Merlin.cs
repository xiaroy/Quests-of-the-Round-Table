using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Merlin : AllyCard
{

    public Merlin() : base("Merlin", 0, 0) //3bid
    {

    }

    public override Ability[] GetAbilities() //no ability
    {
        return null;
        //not yet implemented
    }

}

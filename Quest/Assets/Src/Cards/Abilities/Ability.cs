using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class Ability {

    private TimeRestriction timing;
    private string name;

    public Ability(string name, TimeRestriction timing)
    {
        this.name = name;
        this.timing = timing;
    }

    public abstract void UseAbility(GameState gState);

    public bool DoesTarget() { return false; }

    public bool CanTarget(Card card) { return false; }

    public string GetName() { return name; }
    public TimeRestriction GetTimeRestriction() { return timing; }
}

public enum TimeRestriction
{
    Anytime,
    Normaltime,
    Passive,
}
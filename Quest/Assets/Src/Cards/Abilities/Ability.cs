using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class Ability {

    private TimeRestriction timing;
    private string name;
    private AdventureCard sourceCard;

    public Ability(string name, AdventureCard sourceCard, TimeRestriction timing)
    {
        this.name = name;
        this.timing = timing;
        this.sourceCard = sourceCard;
    }

    public abstract void UseAbility(GameState gState, Player sourcePlayer);

    public virtual bool DoesTarget() { return false; }

    public virtual bool CanTarget(Object obj) { return false; }
    public virtual void SetTarget(Object obj) { }

    public string GetName() { return name; }
    public AdventureCard getSourceCard() { return sourceCard; }
    public TimeRestriction GetTimeRestriction() { return timing; }
}

public enum TimeRestriction
{
    Anytime,
    Normaltime,
    Passive,
}
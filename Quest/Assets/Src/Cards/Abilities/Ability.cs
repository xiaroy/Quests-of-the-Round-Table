using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class Ability {
    
    private string name;
    private AdventureCard sourceCard;

    public Ability(string name, AdventureCard sourceCard)
    {
        this.name = name;
        this.sourceCard = sourceCard;
    }

    public abstract bool CanUseAbility(GameState gState, Player sourcePlayer);
    public abstract void UseAbility(GameState gState, Player sourcePlayer);

    public virtual bool DoesTarget() { return false; }

    public virtual bool CanTarget(Object obj) { return false; }
    public virtual void SetTarget(Object obj) { }

    public string GetName() { return name; }
    public AdventureCard getSourceCard() { return sourceCard; }
}
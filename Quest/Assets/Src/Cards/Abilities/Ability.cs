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

    public virtual bool DoesTarget(GameState gState) { return false; }

    public virtual bool CanTarget(GameState state, Targetable obj) { return false; }
    public virtual void SetTarget(GameState state, Targetable obj) { }

    public string GetName() { return name; }
    public AdventureCard getSourceCard() { return sourceCard; }
}
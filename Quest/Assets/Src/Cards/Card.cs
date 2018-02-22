using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Card : ScriptableObject{

    protected string name;
    protected CardTypes type;
    public Sprite artwork;

    public Card(string name, CardTypes type)
    {
        this.name = name;
        this.type = type;
    }

    public string getName() { return name; }
    public CardTypes getType() { return type; }
}

public enum CardTypes
{
    Event,
    Quest,
    Tournament,
    Weapon,
    Ally,
    Foe,
    Test,
}

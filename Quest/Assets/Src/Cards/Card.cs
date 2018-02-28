using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

[System.Serializable]
public abstract class Card : MonoBehaviour{

    public string name;
    protected CardTypes type;
    public Sprite artwork;

    public Card(string name, CardTypes type)
    {
        this.name = name;
        this.type = type;
    }

    /// <summary>
    /// Gets the name of this card
    /// </summary>
    /// <returns>The name of this card</returns>
    public string getName() { return name; }
    /// <summary>
    /// Gets the type of card this is
    /// </summary>
    /// <returns>The type of card this is</returns>
    public CardTypes GetCardType() { return type; }
}

public enum CardTypes
{
    Event,
    Quest,
    Tournament,
    Weapon,
    Amour,
    Ally,
    Foe,
    Test,
}

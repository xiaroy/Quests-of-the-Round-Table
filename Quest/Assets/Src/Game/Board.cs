using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Board : CardSpace<AdventureCard>
{
    private List<AdventureCard> cardsInPlay = new List<AdventureCard>();

    public bool AddCard(AdventureCard card)
    {
        cardsInPlay.Add(card);
        return true;
    }

    public bool ContainsCard(AdventureCard card)
    {
        return cardsInPlay.Contains(card);
    }

    public AdventureCard[] GetCards()
    {
        return cardsInPlay.ToArray();
    }

    public bool RemoveCard(AdventureCard card)
    {
        return cardsInPlay.Remove(card);
    }

    public int TotalCards()
    {
        return cardsInPlay.Count;
    }
}

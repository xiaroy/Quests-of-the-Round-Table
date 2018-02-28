using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Board : CardSpace<AdventureCard>
{
    private List<AdventureCard> cardsInPlay = new List<AdventureCard>();

    public bool AddCard(AdventureCard card)
    {
        if (CanAddCard(card))
        {
            cardsInPlay.Add(card);
            return true;
        }
        return false;
    }

    public bool CanAddCard(AdventureCard card)
    {
        foreach (AdventureCard c in cardsInPlay)
        {
            if (card.getName().Equals(c.getName()))
                return false;
        }
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

    public int getBoardBattlePoints(GameState state)
    {
        int totalBP = 0;
        foreach (AdventureCard card in cardsInPlay)
            totalBP += card.getBattlePoints(state);
        return totalBP;
    }

    public int getBoardFreeBids(GameState state)
    {
        int total = 0;
        foreach (AdventureCard card in cardsInPlay)
            total += card.getBidPower(state);
        return total;
    }

    public void RemoveAllCardsWithCriteria(CardCriteria criteria)
    {
        List<AdventureCard> remove = new List<AdventureCard>();
        foreach (AdventureCard card in cardsInPlay)
            if (criteria.DoesMeetCriteria(card))
                remove.Add(card);
        foreach (AdventureCard card in remove)
            cardsInPlay.Remove(card);
    }
}

public interface CardCriteria
{
    bool DoesMeetCriteria(Card card);
}

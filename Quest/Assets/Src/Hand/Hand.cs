using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

public class Hand : CardSpace<AdventureCard>
{
    private int currNumCards;
    private List<AdventureCard> cardsInHand = new List<AdventureCard>(); 

    public int TotalCards()
    {
        return currNumCards;
    }

    /*
        Function : contains
        Purpose  : checks if card exist in list 
        Return   : 0 if card not found, 1 if card found
    */
    public bool ContainsCard(AdventureCard card)
    {
        return cardsInHand.Contains(card);
    }

    /*
        Function : addCard
        Purpose  : adds new Adventure card to Player's list
                    if the current amount of card held is less than 13
        Return   : 0 if card not added, 1 if card added
    */
    public bool AddCard(AdventureCard newCard)
    {
        if (cardsInHand.Count >= 12) return false;
        cardsInHand.Add(newCard);
        return true;
    }

    /*
        Function : removeCard
        Purpose  : removes Adventure card to Player's list
                    if the current amount of card held is less than 13
        Return   : 0 if card not removed, 1 if card removed
    */
    public bool RemoveCard(AdventureCard remCard)
    {
        return cardsInHand.Remove(remCard);
    }

    /*
        Function : revealHand
        Purpose  : returns all cards currently in player's list 
        Return   : List of Cards
    */
    public AdventureCard[] GetCards()
    {
        return cardsInHand.ToArray();
    }

    public int GetTotalBids(GameState state)
    {
        return cardsInHand.Count;
    }

    
}
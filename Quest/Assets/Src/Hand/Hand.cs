using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

public class Hand
{
    private int currNumCards;
    private List<AdventureCard> cardsInHand = new List<AdventureCard>(); 

    public int getNumCards()
    {
        return currNumCards;
    }

    /*
        Function : contains
        Purpose  : checks if card exist in hand 
        Return   : 0 if card not found, 1 if card found
    */
    public bool handContains(AdventureCard card)
    {
        if (cardsInHand.Contains(card)) return true;
        return false;
    }

    /*
        Function : addCard
        Purpose  : adds new Adventure card to Player's hand
                    if the current amount of card held is less than 13
        Return   : 0 if card not added, 1 if card added
    */
    public bool addCard(AdventureCard newCard)
    {
        if (cardsInHand.Count >= 12) return false;
        cardsInHand.Add(newCard);
        currNumCards++;
        return true;
    }

    /*
        Function : removeCard
        Purpose  : removes Adventure card to Player's hand
                    if the current amount of card held is less than 13
        Return   : 0 if card not removed, 1 if card removed
    */
    public bool removeCard(AdventureCard remCard)
    {
        if (!handContains(remCard)) return false;
        cardsInHand.Remove(remCard);
        currNumCards--;
        return true;
    }

    /*
        Function : revealHand
        Purpose  : returns all cards currently in player's hand 
        Return   : List of Cards
    */
    public List<AdventureCard> revealHand()
    {
        return cardsInHand;
    }

    
}
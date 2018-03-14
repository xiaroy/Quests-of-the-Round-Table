package model.player;

import java.util.ArrayList;

import model.cards.AdventureCard;
import model.game.CardSpace;
import model.game.GameState;

public class Hand implements CardSpace<AdventureCard> {

	private int currNumCards;
    private ArrayList<AdventureCard> cardsInHand = new ArrayList<>(); 

    public int TotalCards()
    {
        return currNumCards;
    }

    /*
        Function : contains
        Purpose  : checks if card exist in hand 
        Return   : 0 if card not found, 1 if card found
    */
    public boolean ContainsCard(AdventureCard card)
    {
        return cardsInHand.contains(card);
    }

    /*
        Function : addCard
        Purpose  : adds new Adventure card to Player's hand
                    if the current amount of card held is less than 13
        Return   : 0 if card not added, 1 if card added
    */
    public boolean AddCard(AdventureCard newCard)
    {
        if (cardsInHand.size() >= 12) return false;
        cardsInHand.add(newCard);
        currNumCards++;
        return true;
    }

    /*
        Function : removeCard
        Purpose  : removes Adventure card to Player's hand
                    if the current amount of card held is less than 13
        Return   : 0 if card not removed, 1 if card removed
    */
    public boolean RemoveCard(AdventureCard remCard)
    {
        if (!ContainsCard(remCard)) return false;
        cardsInHand.remove(remCard);
        currNumCards--;
        return true;
    }

    /*
        Function : revealHand
        Purpose  : returns all cards currently in player's hand 
        Return   : List of Cards
    */
    public AdventureCard[] GetCards()
    {
        return cardsInHand.toArray(new AdventureCard[cardsInHand.size()]);
    }

    public int GetTotalBids(GameState state)
    {
        return cardsInHand.size();
    }
}

package model.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import model.cards.AdventureCard;
import model.cards.Card;

public class Deck <T extends Card> implements CardSpace <T> {
	
	Deque<T> aDeck = new ArrayDeque<>(); //Queue for the Deck

	public T Draw () //draw a card 
    {
		if (aDeck.isEmpty())
			return null;
        return aDeck.pop(); //Dequeue from Deck
    }
	
	@Override
	public boolean AddCard(T card) {
		aDeck.add(card);
		return true;
	}

	@Override
	public boolean RemoveCard(T card) {
		return false;
	}

	@Override
	public boolean ContainsCard(T card) {
		return aDeck.contains(card);
	}

	@Override
	public int TotalCards() {
		return aDeck.size();
	}

	@Override
	public T[] GetCards() {
		return (T[]) aDeck.toArray(new Card[aDeck.size()]);
	}
	
	public void Shuffle() 
    {
        ArrayList<T> tempDeck = new ArrayList<>(); //List used to store components of Queue for shuffling
        int n = aDeck.size(); //number of cards

        while (n > 0) //remove all components of deck and add it to temp deck for shuffle
        {
            n--;
            tempDeck.add(aDeck.pop());
        }

        for (int j = 0; j < 3; j++) {
	        for (int i = tempDeck.size() - 1; i >= 0; i--) // randomize list by switching two cards by the number of cards there are in the deck
	        {
	            int r1 = (int)Math.floor(Math.random() * tempDeck.size()); //random r between 0 to max deck count
	
	            T random1 = tempDeck.get(r1); //Swap 1 card with another
	            T random2 = tempDeck.get(i);
	            T temp = random1;
	
	            tempDeck.set(r1, random2);
	            tempDeck.set(i, temp);
	        }
        }

        for (int i = tempDeck.size() - 1; i >= 0; i--) //add it back into the deck
        {
            aDeck.push(tempDeck.get(i));
        }

    }

}

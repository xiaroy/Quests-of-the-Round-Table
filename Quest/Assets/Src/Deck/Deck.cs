﻿using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;


public class Deck : CardSpace<Card>{

    Queue<Card> aDeck = new Queue<Card>(); //Queue for the Deck


    public Card Draw () //draw a card 
    {
        if (aDeck.Count == 0)
            return null;
        return (Card)aDeck.Dequeue(); //Dequeue from Deck
    }

    public bool AddCard(Card card) //adds a card void
    {
        aDeck.Enqueue(card);//Enqueue card
        return true;
    }

    public bool RemoveCard(Card card)
    {
        return false;
    }

    public bool ContainsCard(Card card)
    {
        return aDeck.Contains(card);
    }

    public int TotalCards()
    {
        return aDeck.Count;
    }

    public Card[] GetCards()
    {
        return aDeck.ToArray();
    }

    public void Shuffle() 
    {
        List<Card> tempDeck = new List<Card>(); //List used to store components of Queue for shuffling
        int n = aDeck.Count; //number of cards

        while (n > 0) //remove all components of deck and add it to temp deck for shuffle
        {
            n--;
            tempDeck.Add(aDeck.Dequeue());
        }

        for (int i = tempDeck.Count - 1; i > 0; i--) // randomize list by switching two cards by the number of cards there are in the deck
        {
            System.Random rand1 = new System.Random(); //var r random number generator 
            int r1 = rand1.Next(tempDeck.Count); //random r between 0 to max deck count

            System.Random rand2 = new System.Random(); //var r random number generator 
            int r2 = rand2.Next(tempDeck.Count); //random r between 0 to max deck count

            Card random1 = tempDeck[r1]; //Swap 1 card with another
            Card random2 = tempDeck[r2];
            Card temp = random1;

            tempDeck[r1] = random2;
            tempDeck[r2] = temp;

        }

        for (int i = tempDeck.Count - 1; i > 0; i--) //add it back into the deck
        {
            aDeck.Enqueue(tempDeck[i]);
        }

    }
}

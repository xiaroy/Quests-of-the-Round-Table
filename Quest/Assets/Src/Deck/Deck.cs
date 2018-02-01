using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;


public class Deck {

    Queue<Card> aDeck = new Queue<Card>();
    List<Card> tempDeck = new List<Card>();


    public Card Draw () //draw a card //unsure
    {
        return (Card)aDeck.Dequeue();
    }

    public void Add(Card card) //adds a card void
    {
        aDeck.Enqueue(card);
    }

    public void Shuffle() 
    {
        int n = aDeck.Count;
        System.Random rand = new System.Random();
        int r = rand.Next(tempDeck.Count);

        while (n > 0)
        {
            n--;
            tempDeck.Add(aDeck.Dequeue());
        }
        for (int i = tempDeck.Count - 1; i > 0; i--)
        {
            Card random = tempDeck[r];
            Card temp = tempDeck[r];
        }
        for (int i = tempDeck.Count - 1; i > 0; i--)
        {
            aDeck.Enqueue(tempDeck[r]);
        }
    }
}

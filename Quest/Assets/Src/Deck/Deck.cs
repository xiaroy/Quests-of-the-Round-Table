using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public abstract class Deck{

    private Card[] aDeck;

    public Deck() {

    }

    public Card Draw () //draw a card //unsure
    {
    return null;
    }

    public void Add(Card card) //adds a card void
    {
        
    }

    public void Shuffle() //shuffles deck void
                          //fisher yates shuffle
    {
      //  var random = new Random();
      // for (int i = aDeck.Length - 1; i > 0; i--)
      //  {
      //      int n = random.Next(i + 1);
      //      Card[] temp = aDeck[i];
      //      aDeck[i] = aDeck[n];
      //      aDeck[n] = temp;
      //  }
    }
}

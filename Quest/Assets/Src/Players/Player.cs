using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Linq;

public abstract class Player {

    private string name;
    private Rank rank;
    private Hand hand;
    private Board board;
    private int battlePoints;

    /*creating a new Player
     * input : name (string) - name of player
     * */
    public Player(string name)
    {
        this.name = name;
        rank = new Rank();
        board = new Board();
        hand = new Hand();
    }
    /*constructor to create a player from given attributes
     * would be good for test cases
     * */
    public Player(string name, Ranks rank, int shields, Hand hand)
    {
        this.name = name;
        this.rank = new Rank(rank, shields);
        this.hand = hand;
        board = new Board();
    }
    //getters
    public string getName() { return name; }

    public Hand getPlayersCards() { return hand; }

    public void getInput()
    {
        
    }

    //setters
    public void setName(String name) { this.name = name; }

    public void addShields(int shields) { rank.AddShields(shields); }

    public Rank GetRank() { return rank; }


    public bool addCard(AdventureCard card) {
        if (hand.AddCard(card) == true){
            Console.WriteLine(" Card added successfullyy");
            return true;
        }else
        {
            Console.WriteLine(" Cannot add card to Hand");
            return false;
        }
    }
    public abstract bool playCard(Card card);

    public bool doIParticipateInTournament() 
    {
        return true;
    }


    public List<Card> cardsToPlayInTournament() //Should be in AIPlayer instead?
    {
        AdventureCard[] cardsInHand = hand.GetCards();
        cardsInHand = cardsInHand.OrderByDescending(x => x.getBattlePoints(null)).ToArray();
        List<Card> cardsToPlay = new List<Card>();
        int currBattlePoints = 0;
        foreach (AdventureCard card in cardsInHand)
        {
            currBattlePoints += card.getBattlePoints(null);
            //Strategy 2 
            if (currBattlePoints >= 50)
            {
                cardsToPlay.Add(card);
                break;
            }
            else
                cardsToPlay.Add(card);
        }
        //remove from hand ?
        //should playPile be a deck ?
        setBattlePoints(currBattlePoints);
        return cardsToPlay;
    }

    public int getBattlePoints()
    {
        return battlePoints;
    }

    public void setBattlePoints(int BP)
    {
        this.battlePoints = BP;
    }
}

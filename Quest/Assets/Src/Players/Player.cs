using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Linq;

public abstract class Player {

    protected string name;
    protected Rank rank;
    protected Hand hand;
    protected Board board;

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

    public AdventureCard[] getPlayersCards() { return hand.GetCards(); }

    public AdventureCard[] getPlayersBoard() { return board.GetCards(); }

    //setters
    public void SetName(String name) { this.name = name; }

    public void AddShields(int shields) { rank.AddShields(shields); }

    public Rank GetRank() { return rank; }


    public bool AddCardToHand(AdventureCard card)
    {
        return hand.AddCard(card);
    }
    public bool AddCardToBoard(AdventureCard card)
    {
        return board.AddCard(card);
    }
    public bool PlayCardFromHandToBoard(AdventureCard card)
    {
        if (hand.ContainsCard(card))
        {
            if (board.AddCard(card))
            {
                hand.RemoveCard(card);
                return true;
            }
        }
        return false;
    }
    

    public int GetBattlePoints(GameState state)
    {
        return board.getBoardBattlePoints(state) + rank.getRankBattlePoints();
    }
}

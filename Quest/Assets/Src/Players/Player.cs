﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Linq;

[System.Serializable]
public class Player : MonoBehaviour {

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
    public string GetName() { return name; }

    public AdventureCard[] getPlayersCards() { return hand.GetCards(); }

    public AdventureCard[] getPlayersBoard() { return board.GetCards(); }

    //setters
    public void SetName(String name) { this.name = name; }

    public void AddShields(int shields) { rank.AddShields(shields); }

    public Rank GetRank() { return rank; }

    public bool CanPlayCardToBoard(AdventureCard card) { return board.CanAddCard(card); }

    public bool AddCardToHand(AdventureCard card) { return hand.AddCard(card); }
    public bool AddCardToBoard(AdventureCard card) { return board.AddCard(card); }
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

    public bool HandContains(AdventureCard card) { return hand.ContainsCard(card); }
    public bool BoardContains(AdventureCard card) { return board.ContainsCard(card); }

    public bool RemoveCardFromHand(AdventureCard card) { return hand.RemoveCard(card); }
    public bool RemoveCardFromBoard(AdventureCard card) { return board.RemoveCard(card); }
    
    public void RemoveCardsFromBoardWithCriteria(CardCriteria criteria) { board.RemoveAllCardsWithCriteria(criteria); }

    public int GetBattlePoints(GameState state)
    {
        return board.getBoardBattlePoints(state) + rank.getRankBattlePoints();
    }

    public int GetMaximumBids(GameState state)
    {
        return board.getBoardFreeBids(state) + hand.GetTotalBids(state);
    }

    public override string ToString()
    {
        string str = name;
        str += "\n\tRank: " + rank.getCurrentRank() + ", " + rank.getCurrentShields();
        str += "\n\tHand: ";
        foreach (AdventureCard card in hand.GetCards())
            str += card.getName() + ", ";
        str += "\n\tBoard: ";
        foreach (AdventureCard card in board.GetCards())
            str += card.getName() + ", ";
        return str + "\n";
    }
}

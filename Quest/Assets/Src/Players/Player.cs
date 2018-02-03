﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

public abstract class Player {

    private string name;
    public int rank, shields;
    private Hand hand;
    private Deck playPile;

    /*creating a new Player
     * input : name (string) - name of player
     * */
    public Player(string name)
    {
        this.name = name;
        this.rank = 1;
        this.shields = 0;
    }
    /*constructor to create a player from given attributes
     * would be good for test cases
     * */
    public Player(string name, int rank, int shields, Hand hand)
    {
        this.name = name;
        this.rank = rank;
        this.shields = shields;
        this.hand = hand;
    }
    //getters
    public string getName() { return name; }

    public Hand getPlayersCards() { return hand; }

    //setters
    public void setName(String name) { this.name = name; }

    public void increaseRank() { rank++; }

    public int currNumShields() { return shields; }


    public bool addCard(Card card) {
        if (hand.AddCard(card) == true){
            Console.WriteLine(" Card added successfullyy");
            return true;
        }else
        {
            Console.WriteLine(" Cannot add card to Hand");
            return false;
        }
    }
    public abstract bool playCard(Hand hand);
}

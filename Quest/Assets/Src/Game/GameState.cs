using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class GameState {

    private Player[] players;
    private int currentTurnPlayer = 0;

    private Deck adventureDeck, storyDeck;

    public void init()
    {

    }

    public void startGame()
    {
        while (true)
        {
            StoryCard currentCard = (StoryCard)storyDeck.Draw();
            currentCard.doEffect(this);

            if (hasAnyPlayerWon() != null)
                break;

            currentTurnPlayer++;
            if (currentTurnPlayer >= players.Length)
                currentTurnPlayer = 0;
        }
    }

	public void startQuest(QuestCard qCard)
    {
        //Find Sponsor
        Player sponsor = null;
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.Length; i++)
        {
            int askPlayer = i % players.Length;
            /*
             * Code to ask player if they want to sponsor the quest
             * if yes sponsor = that player and break;
             */
        }

        /*
         * 
         */
    }

    public void startTournament(TournamentCard tCard)
    {
        List<Player> participants = new List<Player>();
        Player drawer = players[currentTurnPlayer];

        foreach (Player p in players)
        {
            if (p.doIParticipateInTournament() == true)
                participants.Add(p);
        }

        if (participants.Count < 2) { return; } //tournament not held

        foreach(Player p in participants) //incorrect order
        {
            AdventureCard advenCard = (AdventureCard)adventureDeck.Draw();
            p.addCard(advenCard);
        }
        foreach (Player p in participants)
        {
           List<Card> toPlay =  p.cardsToPlayInTournament();
            //Do something with the cards player is playing..
        }

        //doesn't deal with ties yet
        Player winner = participants.OrderByDescending(x => x.getBattlePoints()).First();
        winner.addShields(tCard.getReward(participants.Count));
    }

    public Player hasAnyPlayerWon()
    {
        return null;
    }
}

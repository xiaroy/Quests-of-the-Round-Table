using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameState {

    private Player[] players;
    private int currentTurnPlayer = 0;

    private Deck adventrueDeck, storyDeck;

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

    }

    public Player hasAnyPlayerWon()
    {
        return null;
    }
}

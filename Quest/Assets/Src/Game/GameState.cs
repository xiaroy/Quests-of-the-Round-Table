using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class GameState {

    private Controller controller;

    private Player[] players;
    private int currentTurnPlayer = 0;

    private Deck adventureDeck, storyDeck;
    private StoryCard currentCard;

    GameTime currentTime;

    public void init()
    {

    }

    public void setController(Controller controller) { this.controller = controller; }

    public void startGame()
    {
        currentTime = GameTime.BetweenStories;
        while (true)
        {
            currentCard = (StoryCard)storyDeck.Draw();
            currentTime = GameTime.InEvent;
            currentCard.doEffect(this);
            currentTime = GameTime.BetweenStories;

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
            //if (p.doIParticipateInTournament() == true)
                participants.Add(p);
        }

        if (participants.Count < 2) { return; } //tournament not held

        foreach(Player p in participants) //incorrect order
        {
            AdventureCard advenCard = (AdventureCard)adventureDeck.Draw();
            p.AddCardToHand(advenCard);
        }
        foreach (Player p in participants)
        {
           //List<Card> toPlay =  p.cardsToPlayInTournament();
            //Do something with the cards player is playing..
        }

        //doesn't deal with ties yet
        Player winner = participants.OrderByDescending(x => x.GetBattlePoints(this)).First();
        winner.AddShields(tCard.getReward(participants.Count));
    }

    /// <summary>
    /// Checks if any player currently won, and returns the winning player, or null if no one has won yet
    /// </summary>
    /// <returns>The player who won, or null if no one has won</returns>
    public Player hasAnyPlayerWon()
    {
        return null;
    }

    // <summary>
    /// Gets the list of players in turn order
    /// </summary>
    /// <returns>The list of players</returns>
    public Player[] getPlayers() { return players; }
    /// <summary>
    /// Gets the player who's turn it currently is
    /// </summary>
    /// <returns>The player who's turn it currently is</returns>
    public Player getCurrentTurnPlayer() { return players[currentTurnPlayer]; }

    public StoryCard getCurrentStoryCard() { return currentCard; }

    /// <summary>
    /// Gets an array of players is order of first place (index 0) to last place (index length - 1)
    /// </summary>
    /// <returns>An ordered list of the palyers from first to last</returns>
    public Player[] getPlayersInRankOrder()
    {
        //Create a new array to sort the players in so that the main player list is left in turn order
        Player[] orderedPlayers = new Player[players.Length];
        for (int i = 0; i < players.Length; i++)
            orderedPlayers[i] = players[i];

        //Sorts the players in order of rank with a simple insertion sort algorithm
        for (int i = 0; i < orderedPlayers.Length; i++)
        {
            for (int j = i + 1; j < orderedPlayers.Length; j++)
            {
                if (orderedPlayers[i].GetRank() > orderedPlayers[j].GetRank())
                {
                    Player temp = orderedPlayers[i];
                    orderedPlayers[i] = orderedPlayers[j];
                    orderedPlayers[j] = temp;
                }
            }
        }
        return orderedPlayers;
    }

    /// <summary>
    /// Gets the Adventure deck
    /// </summary>
    /// <returns>The Adventure deck</returns>
    public Deck getAdventureDeck() { return adventureDeck; }
    /// <summary>
    /// Gets the Story deck
    /// </summary>
    /// <returns>The Story deck</returns>
    public Deck getStoryDeck() { return storyDeck; }

    public bool CanUseAbilityNow(Player source, Ability ability)
    {
        return ability.CanUseAbility(this, source);
    }

    public void UseAbilities(Player source, Ability[] abilities)
    {
        foreach (Ability ability in abilities)
            ability.UseAbility(this, source);
    }

    public GameTime getCurrentGameTime() { return currentTime; }

    public bool AddCardToQuestInfo(AdventureCard card)
    {
        return false;
    }
}

public enum GameTime
{
    SelectSponor,
    SelectQuestEnemies,
    SelectCardsForQuest,
    InQuest,
    SelectCardsForTournament,
    InTournament,
    InEvent,
    BetweenStories,
}

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

    public void Init()
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

            if (HasAnyPlayerWon() != null)
                break;

            currentTurnPlayer++;
            if (currentTurnPlayer >= players.Length)
                currentTurnPlayer = 0;
        }
    }

	public void startQuest(QuestCard qCard)
    {
        //Find Sponsor
        currentTime = GameTime.SelectSponor;
        Player sponsor = null;
        bool[] response = null;
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.Length; i++)
        {
            int askPlayer = i % players.Length;
            response = controller.PromptUserQuestion(new Player[] { players[askPlayer] }, "Would you like to sponsor this Quest?");
            if (response[0])
            {
                sponsor = players[askPlayer];
                break;
            }
        }

        //Check if anyone sponsored the quest
        if (sponsor == null)
            return;

        //Asking the sponor to set up the quest
        currentTime = GameTime.SelectQuestEnemies;
        QuestInfo info = new QuestInfo(qCard, sponsor);
        controller.PromptUserToPlay(new Player[] { sponsor }, "Please set up the Quest");

        //Getting all other not Sponsor player to ask to be in the quest
        currentTime = GameTime.InQuest;
        Player[] nonSponsors = new Player[players.Length - 1];
        int index = 0;
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.Length; i++)
        {
            if (players[i % players.Length] == sponsor)
                continue;
            nonSponsors[index] = players[i % players.Length];
            index++;
        }
        //Asking all the not sponsoring players to participate in the quest
        response = controller.PromptUserQuestion(nonSponsors, "Would you like to participate in this Quest?");
        List<Player> pariticipants = new List<Player>();
        for (int i = 0; i < nonSponsors.Length; i++)
            if (response[i])
                pariticipants.Add(nonSponsors[i]);
        
        //Quest loop to loop for each stage in the quest
        for (int q = 0; q < info.GetNumberOfStages() && pariticipants.Count > 0; q++)
        {
            //Asking the quest participants if they want to play anything for this stage
            currentTime = GameTime.SelectCardsForQuest;
            controller.PromptUserToPlay(pariticipants.ToArray(), "Would you like to play anything for this stage?");
            currentTime = GameTime.InQuest;

            //Checking which participants failed the quest
            foreach (Player p in pariticipants)
                if (p.GetBattlePoints(this) < info.GetBattlePointsForStage(q, this))
                    pariticipants.Remove(p);

            //Giving all the victors an additional card
            foreach (Player p in pariticipants)
                p.AddCardToHand((AdventureCard)adventureDeck.Draw());
        }

        //Giving the sponor cards equal to the amount of cards they used to sponsor the quest
        for (int i = 0; i < info.getTotalCardsPlayed() + info.GetNumberOfStages(); i++)
            sponsor.AddCardToHand((AdventureCard)adventureDeck.Draw());

        //Giving successful participants the required shields
        foreach (Player p in pariticipants)
            p.AddShields(qCard.getReward());

        //Removing all amour and weapons from each players board
        foreach (Player p in players)
            p.RemoveCardsFromBoardWithCriteria(new EndQuestCardCriteria());
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
    public Player HasAnyPlayerWon()
    {
        Player[] rankOrder = getPlayersInRankOrder();
        if (rankOrder[0].GetRank().getCurrentRank() == Ranks.KnightOfRoundTable)
            return rankOrder[0];
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

    private class EndQuestCardCriteria : CardCriteria
    {
        public bool DoesMeetCriteria(Card card)
        {
            return (card.getType() == CardTypes.Amour || card.getType() == CardTypes.Weapon);
        }
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

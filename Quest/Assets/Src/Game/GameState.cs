using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class GameState {

    private ControllerHub controller;

    private Player[] players;
    private int currentTurnPlayer = 0;

    private Deck adventureDeck, storyDeck;
    private StoryCard currentCard;
    private QuestInfo currentQuest;

    GameTime currentTime;

    public GameState(int numOfPlayers)
    {
        players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++)
            players[i] = new Player("Player " + i);

        adventureDeck = new Deck();
        storyDeck = new Deck();

        Init();
    }

    public GameState(Player[] players, Deck adventureDeck, Deck storyDeck)
    {
        this.players = players;
        this.adventureDeck = adventureDeck;
        this.storyDeck = storyDeck;
    }

    public void Init()
    {
        //Populating the adventure deck
        for (int i = 0; i < 16; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Sword));
        for (int i = 0; i < 11; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Horse));
        for (int i = 0; i < 8; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.BattleAx));
        for (int i = 0; i < 6; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Dagger));
        for (int i = 0; i < 6; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Lance));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Excalibur));
        for (int i = 0; i < 8; i++)
            adventureDeck.AddCard(new AmourCard());
        for (int i = 0; i < 8; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Thieves));
        for (int i = 0; i < 8; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.SaxonKnight));
        for (int i = 0; i < 7; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.RobberKnight));
        for (int i = 0; i < 6; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.EvilKnight));
        for (int i = 0; i < 5; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Saxons));
        for (int i = 0; i < 4; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Boar));
        for (int i = 0; i < 4; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Mordred));
        for (int i = 0; i < 3; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.BlackKnight));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.GreenKnight));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Giant));
        for (int i = 0; i < 1; i++)
            adventureDeck.AddCard(new FoeCard(FoeTypes.Dragon));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new TestCard(TestTypes.Valor));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new TestCard(TestTypes.Temptation));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new TestCard(TestTypes.MorganLeFey));
        for (int i = 0; i < 2; i++)
            adventureDeck.AddCard(new TestCard(TestTypes.QuestingBeast));
        adventureDeck.AddCard(new SirGalahad());
        adventureDeck.AddCard(new SirLancelot());
        adventureDeck.AddCard(new KingArthur());
        adventureDeck.AddCard(new SirTristan());
        adventureDeck.AddCard(new KingPellinore());
        adventureDeck.AddCard(new SirGawain());
        adventureDeck.AddCard(new SirPercival());
        adventureDeck.AddCard(new QueenGuinevere());
        adventureDeck.AddCard(new QueenIseult());
        adventureDeck.AddCard(new Merlin());
        adventureDeck.Shuffle();

        //Populating the Story deck
        for (int i = 0; i < 2; i++)
            storyDeck.AddCard(new BoarHunt());
        storyDeck.AddCard(new ChivalrousDeed());
        storyDeck.AddCard(new ProsperityEvent());
        storyDeck.Shuffle();

        for (int i = 0; i < 12; i++)
            foreach (Player p in players)
                p.AddCardToHand((AdventureCard)adventureDeck.Draw());
    }

    public void setController(ControllerHub controller) { this.controller = controller; }

    public void startGame()
    {
        currentTime = GameTime.BetweenStories;
        while (true)
        {
            currentCard = (StoryCard)storyDeck.Draw();
            if (currentCard == null)
                break;
            Debug.Log(currentCard.getName() + " was drawn");
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
        currentCard = qCard;
        currentQuest = new QuestInfo(qCard);

        //Find Sponsor
        currentTime = GameTime.SelectSponor;
        Player sponsor = null;
        ControllerResponse[] response = null;
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.Length; i++)
        {
            int askPlayer = i % players.Length;
            response = controller.PromptUserInput(new Player[] { players[askPlayer] }, ControllerMessageType.WillSponsor);
            if (response[0] == ControllerResponse.Yes)
            {
                sponsor = players[askPlayer];
                Debug.Log(sponsor.GetName() + " choose to sponsor");
                break;
            }
            Debug.Log(players[askPlayer].GetName() + " declined to sponsor");
        }

        //Check if anyone sponsored the quest
        if (sponsor == null) {
            currentQuest = null;
            return;
        }

        //Asking the sponor to set up the quest
        currentTime = GameTime.SelectQuestEnemies;
        currentQuest.SetSponsor(sponsor);
        controller.PromptUserInput(new Player[] { sponsor }, ControllerMessageType.CreateQuestStages);

        //Logging all the cards in this quest
        for (int i = 0; i < currentQuest.GetNumberOfStages(); i++)
        {
            string str = "Stage " + i + "\n\t";
            foreach (AdventureCard card in currentQuest.GetCardsForStage(i))
                str += card.getName() + ", ";
            Debug.Log(str);
        }

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
        response = controller.PromptUserInput(nonSponsors, ControllerMessageType.ParticipateInQuest);
        List<Player> pariticipants = new List<Player>();
        for (int i = 0; i < nonSponsors.Length; i++)
        {
            if (response[i] == ControllerResponse.Yes)
            {
                Debug.Log(nonSponsors[i].GetName() + " has joined the quest");
                pariticipants.Add(nonSponsors[i]);
            }
            else Debug.Log(nonSponsors[i].GetName() + " declined the quest");
        }
        
        //Quest loop to loop for each stage in the quest
        for (int q = 0; q < currentQuest.GetNumberOfStages() && pariticipants.Count > 0; q++)
        {
            currentQuest.setCurStage(q);
            //Asking the quest participants if they want to play anything for this stage
            currentTime = GameTime.SelectCardsForQuest;
            controller.PromptUserInput(pariticipants.ToArray(), ControllerMessageType.PlayForQuest);
            currentTime = GameTime.InQuest;

            //Checking which participants failed the quest
            List<Player> remove = new List<Player>();
            for (int i = pariticipants.Count - 1; i >= 0; i--)
            {
                string str = pariticipants[i].GetName() + "\n\t";
                foreach (AdventureCard card in pariticipants[i].getPlayersBoard())
                    str += card.getName() + ", ";
                Debug.Log(str);

                if (pariticipants[i].GetBattlePoints(this) < currentQuest.GetBattlePointsForStage(q, this))
                {
                    Debug.Log(pariticipants[i].GetName() + " fail the failed");
                    pariticipants.RemoveAt(i);
                }
                else Debug.Log(pariticipants[i].GetName() + " continues the quest");
            }

            //Giving all the victors an additional card
            foreach (Player p in pariticipants)
                p.AddCardToHand((AdventureCard)adventureDeck.Draw());
        }

        //Giving the sponor cards equal to the amount of cards they used to sponsor the quest
        for (int i = 0; i < currentQuest.getTotalCardsPlayed() + currentQuest.GetNumberOfStages(); i++)
            sponsor.AddCardToHand((AdventureCard)adventureDeck.Draw());

        //Giving successful participants the required shields
        foreach (Player p in pariticipants)
        {
            Debug.Log(p.GetName() + " won the quest");
            p.AddShields(qCard.getReward());
        }

        //Removing all amour and weapons from each players board
        foreach (Player p in players)
            p.RemoveCardsFromBoardWithCriteria(new EndQuestCardCriteria());

        currentQuest = null;
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

    public QuestInfo getCurrentQuest() { return currentQuest; }

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

    private class EndQuestCardCriteria : CardCriteria
    {
        public bool DoesMeetCriteria(Card card)
        {
            return (card.GetCardType() == CardTypes.Amour || card.GetCardType() == CardTypes.Weapon);
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

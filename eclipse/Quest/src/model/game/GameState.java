package model.game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ControllerHub;
import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.*;
import model.cards.Card.CardTypes;
import model.cards.FoeCard.FoeTypes;
import model.cards.QuestCard;
import model.cards.StoryCard;
import model.cards.TestCard.TestTypes;
import model.cards.TournamentCard;
import model.cards.WeaponCard;
import model.cards.WeaponCard.WeaponTypes;
import model.cards.abilities.Ability;
import model.cards.abilities.WeaponAbility;
import model.cards.allyCards.*;
import model.cards.eventCards.*;
import model.player.Player;
import model.player.Rank;
import model.player.Rank.Ranks;

public class GameState {

	private ControllerHub controller;

    private Player[] players;
    private int currentTurnPlayer = 0;

    private Deck<AdventureCard> adventureDeck;
    private Deck<StoryCard> storyDeck;
    private StoryCard currentCard;
    private QuestInfo currentQuest;

    GameTime currentTime;
    
    public GameState(int numOfPlayers) {
    	players = new Player[numOfPlayers];
    	for (int i = 0; i < numOfPlayers; i++)
    		players[i] = new Player("Player " + i);
    	
    	adventureDeck = new Deck<>();
    	storyDeck = new Deck<>();
    	
    	Init();
    }
    
    public GameState(Player[] players) {
    	this.players = players;
    	
    	adventureDeck = new Deck<>();
    	storyDeck = new Deck<>();
    	
    	Init();
    }
    
    public GameState(Player[] players, Deck<AdventureCard> adventureDeck, Deck<StoryCard> storyDeck) {
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
    	adventureDeck.AddCard(new SirPellinore());
    	adventureDeck.AddCard(new SirGawain());
    	adventureDeck.AddCard(new SirPercival());
    	adventureDeck.AddCard(new QueenGuinevere());
    	adventureDeck.AddCard(new QueenIseult());
    	adventureDeck.AddCard(new Merlin());
    	adventureDeck.Shuffle();
    	
    	//Populating the Story deck
    	for (int i = 0; i < 2; i++)
    		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.BoarHunt));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.DefendQueenHonor));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.EnchantedForest));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.GreenKnight));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.HolyGrail));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.QuestingBeast));
    	for (int i = 0; i < 2; i++)
    		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.RepelSaxons));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.RescueFairMaiden));
    	for (int i = 0; i < 2; i++)
    		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.SlayDragon));
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.VanquishArhtursEnemies));
    	storyDeck.AddCard(new ChilvalrousDeed());
    	storyDeck.AddCard(new ProsperityEvent());
    	for (int i = 0; i < 2; i++)
    		storyDeck.AddCard(new CourtToCamalot());
    	storyDeck.AddCard(new PlagueEvent());
    	storyDeck.AddCard(new PoxEvent());
    	for (int i = 0; i < 2; i++)
    		storyDeck.AddCard(new QueensFavorEvent());
    	storyDeck.Shuffle();
    	
    	for (int i = 0; i < 12; i++)
    		for (Player p : players)
    			p.AddCardToHand(adventureDeck.Draw());
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
            System.out.println("Story Card " + currentCard.getName() + " was drawn");
            controller.sendUIMessage(currentCard.getName() + " was drawn for the turn");
            Logger.getLogger(GameState.class.getName()).log(Level.FINE, currentCard.getName() + " was drawn for the turn");
            controller.updateView();
            controller.PromptUserInput(players, ControllerMessageType.Continue);
            currentTime = GameTime.InEvent;
            currentCard.doEffect(this);
            currentTime = GameTime.BetweenStories;

            if (HasAnyPlayerWon() != null)
                break;

            currentTurnPlayer++;
            if (currentTurnPlayer >= players.length)
                currentTurnPlayer = 0;
        }
        
        String whoWon = "";
        Player[] rankPlayer = getPlayersInRankOrder();
        Rank top = rankPlayer[0].GetRank();
        for (int i = 0; i < rankPlayer.length; i++) {
        	System.out.println(rankPlayer[i].GetName() + ", ");
        	if (top.getCurrentRank() == rankPlayer[i].GetRank().getCurrentRank() && top.getCurrentShields() == rankPlayer[i].GetRank().getCurrentShields()) {
        		if (i > 0)
        			whoWon += ", ";
        		whoWon += rankPlayer[i].GetName();
        	}
        }
        whoWon += " has won the game";
        controller.sendUIMessage(whoWon);
        Logger.getLogger(GameState.class.getName()).log(Level.FINE, whoWon);
        System.out.println("The Game Has Ended");
    }

	public void startQuest(QuestCard qCard)
    {
		currentCard = qCard;
        currentQuest = new QuestInfo(qCard);

        //Find Sponsor
        currentTime = GameTime.SelectSponor;
        Player sponsor = null;
        ControllerResponse[] response = null;
        controller.updateView();
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.length; i++)
        {
            int askPlayer = i % players.length;
            response = controller.PromptUserInput(new Player[] { players[askPlayer] }, ControllerMessageType.WillSponsor);
            if (response[0] == ControllerResponse.Yes)
            {
                sponsor = players[askPlayer];
                controller.sendUIMessage(sponsor.GetName() + " choose to sponsor");
                Logger.getLogger(GameState.class.getName()).log(Level.FINE, sponsor.GetName() + " choose to sponsor");
                break;
            }
            controller.sendUIMessage(players[askPlayer].GetName() + " declined to sponsor");
            Logger.getLogger(GameState.class.getName()).log(Level.FINE, players[askPlayer].GetName() + " declined to sponsor");
        }

        //Check if anyone sponsored the quest
        if (sponsor == null) {
            currentQuest = null;
            return;
        }

        //Asking the sponor to set up the quest
        currentTime = GameTime.SelectQuestEnemies;
        currentQuest.SetSponsor(sponsor);
        currentQuest.setCurStage(-1);
        controller.PromptUserInput(new Player[] { sponsor }, ControllerMessageType.CreateQuestStages);
        currentQuest.setCurStage(0);

        //Logging all the cards in this quest
        for (int i = 0; i < currentQuest.GetNumberOfStages(); i++)
        {
            String str = "Stage " + i + "\n\t";
            for (AdventureCard card : currentQuest.GetCardsForStage(i))
                str += card.getName() + ", ";
            System.out.println(str);
        }
        
        controller.updateView();
        //Getting all other not Sponsor player to ask to be in the quest
        currentTime = GameTime.InQuest;
        Player[] nonSponsors = new Player[players.length - 1];
        int index = 0;
        for (int i = currentTurnPlayer; i < currentTurnPlayer + players.length; i++)
        {
            if (players[i % players.length] == sponsor)
                continue;
            nonSponsors[index] = players[i % players.length];
            index++;
        }
        //Asking all the not sponsoring players to participate in the quest
        response = controller.PromptUserInput(nonSponsors, ControllerMessageType.ParticipateInQuest);
        ArrayList<Player> pariticipants = new ArrayList<>();
        for (int i = 0; i < nonSponsors.length; i++)
        {
            if (response[i] == ControllerResponse.Yes)
            {
            	controller.sendUIMessage(nonSponsors[i].GetName() + " has joined the quest");
            	Logger.getLogger(GameState.class.getName()).log(Level.FINE, nonSponsors[i].GetName() + " has joined the quest");
                pariticipants.add(nonSponsors[i]);
            }
            else {
            	controller.sendUIMessage(nonSponsors[i].GetName() + " declined the quest");
            	Logger.getLogger(GameState.class.getName()).log(Level.FINE, nonSponsors[i].GetName() + " declined the quest");
            }
        }
        
        //Quest loop to loop for each stage in the quest
        for (int q = 0; q < currentQuest.GetNumberOfStages() && pariticipants.size() > 0; q++)
        {
        	controller.updateView();
            currentQuest.setCurStage(q);
            //Asking the quest participants if they want to play anything for this stage
            currentTime = GameTime.SelectCardsForQuest;
            controller.PromptUserInput(pariticipants.toArray(new Player[pariticipants.size()]), ControllerMessageType.PlayForQuest);
            currentTime = GameTime.InQuest;
            String stageResults = "";
            controller.updateView();

            //Checking which participants failed the quest
            for (int i = pariticipants.size() - 1; i >= 0; i--)
            {
                String str = pariticipants.get(i).GetName() + "\n\t";
                for (AdventureCard card : pariticipants.get(i).getPlayersBoard())
                    str += card.getName() + ", ";
                System.out.println(str);
                
                if (pariticipants.get(i).GetBattlePoints(this) < currentQuest.GetBattlePointsForStage(q, this))
                {
                	stageResults += pariticipants.get(i).GetName() + " failed the quest\n";
                	controller.sendUIMessage(pariticipants.get(i).GetName() + " failed the quest");
                	Logger.getLogger(GameState.class.getName()).log(Level.FINE, pariticipants.get(i).GetName() + " failed the quest");
                    pariticipants.remove(i);
                }
                else {
                	stageResults += pariticipants.get(i).GetName() + " continues the quest\n";
                	Logger.getLogger(GameState.class.getName()).log(Level.FINE, pariticipants.get(i).GetName() + " continues the quest");
                	controller.sendUIMessage(pariticipants.get(i).GetName() + " continues the quest");
                }
            }

            currentQuest.setCurStage(q+1);
            System.out.println(stageResults);
            //Giving all the victors an additional card
            for (Player p : pariticipants)
                p.AddCardToHand((AdventureCard)adventureDeck.Draw());
        }
        currentQuest.setCurStage(currentQuest.GetNumberOfStages());

        //Giving the sponor cards equal to the amount of cards they used to sponsor the quest
        for (int i = 0; i < currentQuest.getTotalCardsPlayed() + currentQuest.GetNumberOfStages(); i++)
            sponsor.AddCardToHand((AdventureCard)adventureDeck.Draw());

        //Giving successful participants the required shields
        String questResults = "";
        for (Player p : pariticipants)
        {
        	questResults += p.GetName() + " completed the quest and recieved + " + qCard.getReward() + " Shields\n";
        	System.out.println(p.GetName() + " completed the quest");
            p.AddShields(qCard.getReward());
        }
        Logger.getLogger(GameState.class.getName()).log(Level.FINE, questResults + "They recieved " + qCard.getReward() + " Shields");

        //Removing all amour and weapons from each players board
        for (Player p : players)
            p.RemoveCardsFromBoardWithCriteria(new EndQuestCardCriteria());

        controller.updateView();
        currentQuest = null;
    }

    public void startTournament(TournamentCard tCard)
    {
        ArrayList<Player> participants = new ArrayList<>();
        Player drawer = players[currentTurnPlayer];

        for (Player p : players)
        {
            //if (p.doIParticipateInTournament() == true)
                participants.add(p);
        }

        if (participants.size() < 2) { return; } //tournament not held

        for(Player p : participants) //incorrect order
        {
            AdventureCard advenCard = (AdventureCard)adventureDeck.Draw();
            p.AddCardToHand(advenCard);
        }
        for (Player p : participants)
        {
           //List<Card> toPlay =  p.cardsToPlayInTournament();
            //Do something with the cards player is playing..
        }

        //doesn't deal with ties yet
        //Player winner = participants.OrderByDescending(x => x.GetBattlePoints(this)).First();
        //winner.AddShields(tCard.getReward(participants.size()));
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
        Player[] orderedPlayers = new Player[players.length];
        for (int i = 0; i < players.length; i++)
            orderedPlayers[i] = players[i];

        //Sorts the players in order of rank with a simple insertion sort algorithm
        for (int i = 0; i < orderedPlayers.length; i++)
        {
            for (int j = i + 1; j < orderedPlayers.length; j++)
            {
                if (orderedPlayers[j].GetRank().greaterThan(orderedPlayers[i].GetRank()))
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
    public Deck<AdventureCard> getAdventureDeck() { return adventureDeck; }
    /// <summary>
    /// Gets the Story deck
    /// </summary>
    /// <returns>The Story deck</returns>
    public Deck<StoryCard> getStoryDeck() { return storyDeck; }

    public boolean CanUseAbilityNow(Player source, Ability ability)
    {
        return ability.CanUseAbility(this, source);
    }

    public void UseAbilities(Player source, Ability[] abilities)
    {
        for (Ability ability : abilities)
            ability.UseAbility(this, source);
    }

    public GameTime getCurrentGameTime() { return currentTime; }

    public boolean AddCardToQuestInfo(AdventureCard card)
    {
        return false;
    }
    
    private class EndQuestCardCriteria implements CardCriteria
    {
        public boolean DoesMeetCriteria(Card card)
        {
            return (card.GetCardType() == CardTypes.Amour || card.GetCardType() == CardTypes.Weapon);
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
	
}

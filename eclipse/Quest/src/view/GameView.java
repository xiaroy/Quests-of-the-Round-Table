package view;

import model.cards.Card;
import model.cards.StoryCard;
import model.game.GameState;
import model.game.QuestInfo;
import model.player.Player;
import model.player.Rank;

public class GameView {

	private Player perspective;
    private OtherPlayer[] others;
    private StoryCard currentStory;
    private QuestInfo currentQuest;

    public GameView(GameState state, Player perspective)
    {
        this.perspective = perspective;
        this.currentStory = state.getCurrentStoryCard();
        this.currentQuest = state.getCurrentQuest();
        int i = 0, perPlayerIndex = 0;
        others = new OtherPlayer[state.getPlayers().length];
        for (Player player : state.getPlayers())
        {
            if (player == perspective) {
            	perPlayerIndex = i;
                continue;
            }
            others[i] = new OtherPlayer();
        	others[i].name = player.GetName();
			others[i].board = player.getPlayersBoard();
			others[i].cardsInHand = player.getPlayersCards().length;
			others[i].rank = player.GetRank();
            i++;
        }
        if (i < others.length)
        {
            OtherPlayer[] newOthers = new OtherPlayer[i];
            for (int i2 = 0; i2 < i; i2++)
                newOthers[i2] = others[(i2 + perPlayerIndex) % i];
            others = newOthers;
        }
    }

    public StoryCard GetCurrentStory() { return currentStory; }
    public QuestInfo getCurrentQuest() { return currentQuest; }

    public String GetPerspectiveName()
    {
        if (perspective == null)
            return others[0].name;
        return perspective.GetName();
    }
    public Card[] GetPerspectiveHand()
    {
        if (perspective == null)
            return new Card[others[0].cardsInHand];
        return perspective.getPlayersCards();
    }
    public Card[] GetPerspectiveBoard()
    {
        if (perspective == null)
            return others[0].board;
        return perspective.getPlayersBoard();
    }
    public Rank GetPerspectiveRank()
    {
        if (perspective == null)
            return others[0].rank;
        return perspective.GetRank();
    }

    public String GetOtherPlayerName(int i) { return others[i].name; }
    public int GetOtherPlayerHandCount(int i) { return others[i].cardsInHand; }
    public Card[] GetOtherPlayerBoard(int i) { return others[i].board; }
    public Rank GetOtherPlayerRank(int i) { return others[i].rank; }
    public int NumberOfOtherPlayers() { return others.length; }


    private class OtherPlayer
    {
        public String name;
        public int cardsInHand;
        public Card[] board;
        public Rank rank;
    }
    
}

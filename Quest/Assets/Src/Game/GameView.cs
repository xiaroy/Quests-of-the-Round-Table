using UnityEngine;
using System.Collections;

public class GameView
{
    private Player perspective;
    private OtherPlayer[] others;
    private StoryCard currentStory;

    public GameView(GameState state, Player perspective)
    {
        this.perspective = perspective;
        this.currentStory = state.getCurrentStoryCard();
        int i = 0;
        others = new OtherPlayer[state.getPlayers().Length];
        foreach (Player player in state.getPlayers())
        {
            if (player == perspective)
                continue;
            others[i] = new OtherPlayer
            {
                name = player.getName(),
                board = player.getPlayersBoard(),
                cardsInHand = player.getPlayersCards().Length,
                rank = player.GetRank()
            };
            i++;
        }
        if (i < others.Length)
        {
            OtherPlayer[] newOthers = new OtherPlayer[i];
            for (int i2 = 0; i2 < i; i2++)
                newOthers[i2] = others[i2];
            others = newOthers;
        }
    }

    public string GetPerspectiveName()
    {
        if (perspective == null)
            return others[0].name;
        return perspective.getName();
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

    public string GetOtherPlayerName(int i) { return others[i].name; }
    public int GetOtherPlayerHandCount(int i) { return others[i].cardsInHand; }
    public Card[] GetOtherPlayerBoard(int i) { return others[i].board; }
    public Rank GetOtherPlayerRank(int i) { return others[i].rank; }
    public int NumberOfOtherPlayers() { return others.Length; }


    private class OtherPlayer
    {
        public string name;
        public int cardsInHand;
        public Card[] board;
        public Rank rank;
    }
}

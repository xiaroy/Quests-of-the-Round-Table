package model.game;

import java.util.ArrayList;

import model.cards.AdventureCard;
import model.cards.QuestCard;
import model.player.Player;

public class QuestInfo {

	private Player sponsor;
    private QuestCard card;
    private QuestStage[] stages;
    private int currentStage = 0;

    public QuestInfo(QuestCard card)
    {
        this.sponsor = null;
        this.card = card;
        stages = new QuestStage[card.getStages()];
        for (int i = 0; i < stages.length; i++)
            stages[i] = new QuestStage(this);
    }

    public QuestInfo(QuestCard card, Player sponsor)
    {
        this.sponsor = sponsor;
        this.card = card;
        stages = new QuestStage[card.getStages()];
        for (int i = 0; i < stages.length; i++)
            stages[i] = new QuestStage(this);
    }

    public void SetSponsor(Player sponsor) { this.sponsor = sponsor; }
    public Player GetSponsor() { return sponsor; }

    public int GetNumberOfStages() { return card.getStages(); }

    public void SetCardsForStage(int i, AdventureCard[] cards)
    {
        ArrayList<AdventureCard> playableCards = new ArrayList<>();
        for (AdventureCard card : cards)
            if (sponsor.HandContains(card))
            {
                playableCards.add(card);
                sponsor.RemoveCardFromHand(card);
            }
    }
    public AdventureCard[] GetCardsForStage(int i) { return stages[i].getCards(); }
    public QuestStage getStage(int i) { return stages[i]; }
    public QuestCard getQuestCard() { return card; }
    public int GetQuestReward() { return card.getReward(); }

    public void setCurStage(int curStage) { currentStage = curStage; }
    public int getCurStage() { return currentStage; }

    public int GetBattlePointsForStage(int i, GameState state)
    {
        int total = 0;
        for (AdventureCard card : stages[i].getCards())
            total += card.getBattlePoints(state);
        return total;
    }

    public int getTotalCardsPlayed()
    {
        int total = 0;
        for (int i = 0; i < stages.length; i++)
            total += stages[i].getCards().length;
        return total;
    }
    
}

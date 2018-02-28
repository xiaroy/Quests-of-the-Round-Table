using System.Collections;
using System.Collections.Generic;
using UnityEngine;

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
        for (int i = 0; i < stages.Length; i++)
            stages[i] = new QuestStage(this);
    }

    public QuestInfo(QuestCard card, Player sponsor)
    {
        this.sponsor = sponsor;
        this.card = card;
        stages = new QuestStage[card.getStages()];
        for (int i = 0; i < stages.Length; i++)
            stages[i] = new QuestStage(this);
    }

    public void SetSponsor(Player sponsor) { this.sponsor = sponsor; }
    public Player GetSponsor() { return sponsor; }

    public int GetNumberOfStages() { return card.getStages(); }

    public void SetCardsForStage(int i, AdventureCard[] cards)
    {
        List<AdventureCard> playableCards = new List<AdventureCard>();
        foreach (AdventureCard card in cards)
            if (sponsor.HandContains(card))
            {
                playableCards.Add(card);
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
        foreach (AdventureCard card in stages[i].getCards())
            total += card.getBattlePoints(state);
        return total;
    }

    public int getTotalCardsPlayed()
    {
        int total = 0;
        for (int i = 0; i < stages.Length; i++)
            total += stages[i].getCards().Length;
        return total;
    }
}

using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class QuestStage : Targetable
{
    private QuestInfo quest;

    List<AdventureCard> cards = new List<AdventureCard>();

    public QuestStage(QuestInfo info)
    {
        this.quest = info;
    }


    public void addCard(AdventureCard card)
    {
        cards.Add(card);
    }

    public int getBattlePoints(GameState state)
    {
        int total = 0;
        foreach (AdventureCard card in cards)
            total += card.getBattlePoints(state);
        return total;
    }

    public AdventureCard[] getCards() { return cards.ToArray(); }
}

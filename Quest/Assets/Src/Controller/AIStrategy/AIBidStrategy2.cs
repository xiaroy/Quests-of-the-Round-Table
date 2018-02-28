using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class AIBidStrategy2 : AIBidStrategy
{
    public Card[] CardsToDiscard(GameState state, Player player)
    {
        List<Card> remove = new List<Card>();
        foreach (AdventureCard card in player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe && card.getBattlePoints(state) <= 25)
                remove.Add(card);
        }

        return remove.ToArray();
    }

    public int NumberOfCardsToBid(GameState state, Player player)
    {
        int counter = 0;
        foreach (AdventureCard card in player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe && card.getBattlePoints(state) <= 25)
                counter++;
        }
        return counter;
    }
}

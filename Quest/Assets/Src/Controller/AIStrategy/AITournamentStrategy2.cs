using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class AITournamentStrategy2 : AITournamentStrategy
{
    public Ability[] CardsToPlay(GameState state, Player player)
    {
        int currentBP = player.GetBattlePoints(state);
        if (currentBP >= 50)
            return null; 

        List<AdventureCard> validCards = new List<AdventureCard>();
        List<string> usedCards = new List<string>();

        foreach (AdventureCard card in player.getPlayersCards())
        {
            if (player.CanPlayCardToBoard(card))
            {
                bool used = false;
                for (int i = 0; i < usedCards.Count && !used; i++)
                    if (usedCards[i].Equals(card.getName()))
                        used = true;
                if (!used)
                {
                    usedCards.Add(card.getName());
                    validCards.Add(card);
                }
            }
        }

        validCards.Sort(new ACardComparer(state));

        List<Ability> abilities = new List<Ability>();
        foreach (AdventureCard card in validCards)
        {
            abilities.Add(card.GetAbilities()[0]);
            currentBP += card.getBattlePoints(state);
            if (currentBP >= 50)
                break;
        }

        return abilities.ToArray();
    }

    public bool DoIParticipateInTournament(GameState state, Player player)
    {
        return true;
    }

    private class ACardComparer : IComparer<AdventureCard>
    {
        GameState state;
        public ACardComparer(GameState state)
        {
            this.state = state;
        }

        public int Compare(AdventureCard x, AdventureCard y)
        {
            if (x.getBattlePoints(state) < y.getBattlePoints(state))
                return 1;
            else if (x.getBattlePoints(state) > y.getBattlePoints(state))
                return -1;
            return 0;
        }
    }
}

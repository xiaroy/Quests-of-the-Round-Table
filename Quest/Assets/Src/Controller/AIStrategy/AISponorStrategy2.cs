using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class AISponorStrategy2 : AIQuestSponorStrategy
{
    public Ability[] CardsToPlay(GameState state, Player player)
    {
        QuestInfo curQuest = state.getCurrentQuest();
        List<FoeCard> foes = new List<FoeCard>();
        List<Ability> cardsToUse = new List<Ability>();

        //Getting all of this players foe cards
        foreach (AdventureCard card in player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe)
                foes.Add((FoeCard)card);
        }

        //Sorting the foes from least BP to most
        foes.Sort(new FoeComparer(state));

        int stage = 0, lastStageBP = 0;
        //Going through each foe and putting them in an appropriate stage
        foreach (FoeCard card in foes)
        {
            //If this stage isn't the last stage and this foe has more BP then the last foe, assign it to this stage
            if (stage < curQuest.GetNumberOfStages() - 1 && lastStageBP < card.getBattlePoints(state))
            {
                Ability curCardAbl = card.GetAbilities()[0];
                curCardAbl.SetTarget(state, curQuest.getStage(stage));
                cardsToUse.Add(curCardAbl);
                lastStageBP = card.getBattlePoints(state);
                stage++;
            }
            //If this stage is the last stage and this foe has at least 40 BP, assign it to this stage
            else if (stage == curQuest.GetNumberOfStages() - 1 && card.getBattlePoints(state) >= 40)
            {
                Ability curCardAbl = card.GetAbilities()[0];
                curCardAbl.SetTarget(state, curQuest.getStage(stage));
                cardsToUse.Add(curCardAbl);
            }
        }

        //Checks if a foe was found for all the stages (only last should be left at this point)
        if (stage < curQuest.GetNumberOfStages())
        {
            stage = curQuest.GetNumberOfStages() - 1;
            //Counting the total BP of all cards in the last stage
            int totalBP = 0;
            //Getting the player's most powerful foe
            Ability curCardAbl = foes[foes.Count - 1].GetAbilities()[0];
            curCardAbl.SetTarget(state, curQuest.getStage(stage));
            cardsToUse.Add(curCardAbl);
            totalBP += foes[foes.Count - 1].getBattlePoints(state);

            //Looping through all the Player's cards to find enough Weapons to make the foe at least 40 BP
            foreach (AdventureCard card in player.getPlayersCards())
            {
                if (totalBP >= 40)
                    break;

                if (card.GetCardType() == CardTypes.Weapon)
                {
                    curCardAbl = card.GetAbilities()[0];
                    curCardAbl.SetTarget(state, curQuest.getStage(stage));
                    cardsToUse.Add(curCardAbl);
                    totalBP += card.getBattlePoints(state);
                }
            }
        }

        return cardsToUse.ToArray();
    }

    public bool DoISponorQuest(GameState state, Player player)
    {
        //Checking if any other player can rank up from completing this quest
        foreach (Player p in state.getPlayers())
        {
            //Skipping if this player
            if (p == player)
                continue;

            //Getting the other players rank and adding the reward to it and checking if the rank changes
            Rank rank = new Rank(p.GetRank().getCurrentRank(), p.GetRank().getCurrentShields());
            rank.AddShields(state.getCurrentQuest().GetQuestReward());
            //If the player will rank up, return false
            if (rank.getCurrentRank() != p.GetRank().getCurrentRank())
                return false;
        }
        
        //Check if this player has enough foes to make this quest
        List<int> takenBP = new List<int>();
        foreach (AdventureCard card in player.getPlayersCards())
        {
            //Counting up number of foes with different BP values
            if (card.GetCardType() == CardTypes.Foe)
            {
                int cardBP = card.getBattlePoints(state);
                if (!takenBP.Contains(cardBP))
                    takenBP.Add(cardBP);
            }
        }
        //Checking foe count compared to number of stages
        return takenBP.Count >= state.getCurrentQuest().GetNumberOfStages();
    }

    private class FoeComparer : IComparer<FoeCard>
    {
        GameState state;
        public FoeComparer(GameState state)
        {
            this.state = state;
        }

        public int Compare(FoeCard x, FoeCard y)
        {
            if (x.getBattlePoints(state) > y.getBattlePoints(state))
                return 1;
            else if (x.getBattlePoints(state) < y.getBattlePoints(state))
                return -1;
            return 0;
        }
    }
}

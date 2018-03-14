package controller.AI;

import java.util.ArrayList;
import java.util.Comparator;

import model.cards.AdventureCard;
import model.cards.Card.CardTypes;
import model.cards.FoeCard;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.game.QuestInfo;
import model.player.Player;
import model.player.Rank;

public class AISponsorStrategy2 implements AISponsorStrategy {

	@Override
	public boolean DoISponorQuest(GameState state, Player player) {
		//Checking if any other player can rank up from completing this quest
        for (Player p : state.getPlayers())
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
        ArrayList<Integer> takenBP = new ArrayList<>();
        for (AdventureCard card : player.getPlayersCards())
        {
            //Counting up number of foes with different BP values
            if (card.GetCardType() == CardTypes.Foe)
            {
                int cardBP = card.getBattlePoints(state);
                if (!takenBP.contains(cardBP))
                    takenBP.add(cardBP);
            }
        }
        //Checking foe count compared to number of stages
        return takenBP.size() >= state.getCurrentQuest().GetNumberOfStages();
	}

	@Override
	public Ability[] CardsToPlay(GameState state, Player player) {
		QuestInfo curQuest = state.getCurrentQuest();
        ArrayList<FoeCard> foes = new ArrayList<>();
        ArrayList<Ability> cardsToUse = new ArrayList<>();

        //Getting all of this players foe cards
        for (AdventureCard card : player.getPlayersCards())
        {
            if (card.GetCardType() == CardTypes.Foe)
                foes.add((FoeCard)card);
        }

        //Sorting the foes from least BP to most
        foes.sort(new Comparator<FoeCard>(){
			@Override
			public int compare(FoeCard foe1, FoeCard foe2) {
				if (foe1.getBattlePoints(state) > foe2.getBattlePoints(state))
	                return 1;
	            else if (foe1.getBattlePoints(state) < foe2.getBattlePoints(state))
	                return -1;
	            return 0;
			}
		});

        int stage = 0, lastStageBP = 0;
        //Going through each foe and putting them in an appropriate stage
        for (FoeCard card : foes)
        {
            //If this stage isn't the last stage and this foe has more BP then the last foe, assign it to this stage
            if (stage < curQuest.GetNumberOfStages() - 1 && lastStageBP < card.getBattlePoints(state))
            {
                Ability curCardAbl = card.GetAbilities()[0];
                curCardAbl.SetTarget(state, curQuest.getStage(stage));
                cardsToUse.add(curCardAbl);
                lastStageBP = card.getBattlePoints(state);
                stage++;
            }
            //If this stage is the last stage and this foe has at least 40 BP, assign it to this stage
            else if (stage == curQuest.GetNumberOfStages() - 1 && card.getBattlePoints(state) >= 40)
            {
                Ability curCardAbl = card.GetAbilities()[0];
                curCardAbl.SetTarget(state, curQuest.getStage(stage));
                cardsToUse.add(curCardAbl);
            }
        }

        //Checks if a foe was found for all the stages (only last should be left at this point)
        if (stage < curQuest.GetNumberOfStages())
        {
            stage = curQuest.GetNumberOfStages() - 1;
            //Counting the total BP of all cards in the last stage
            int totalBP = 0;
            //Getting the player's most powerful foe
            Ability curCardAbl = foes.get(foes.size() - 1).GetAbilities()[0];
            curCardAbl.SetTarget(state, curQuest.getStage(stage));
            cardsToUse.add(curCardAbl);
            totalBP += foes.get(foes.size() - 1).getBattlePoints(state);

            //Looping through all the Player's cards to find enough Weapons to make the foe at least 40 BP
            for (AdventureCard card : player.getPlayersCards())
            {
                if (totalBP >= 40)
                    break;

                if (card.GetCardType() == CardTypes.Weapon)
                {
                    curCardAbl = card.GetAbilities()[0];
                    curCardAbl.SetTarget(state, curQuest.getStage(stage));
                    cardsToUse.add(curCardAbl);
                    totalBP += card.getBattlePoints(state);
                }
            }
        }

        return cardsToUse.toArray(new Ability[cardsToUse.size()]);
	}

}

package controller.AI;

import java.util.ArrayList;
import java.util.Comparator;

import model.cards.AdventureCard;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;

public class AITournamentStrategy2 implements AITournamentStrategy {

	@Override
	public boolean DoIParticipateInTournament(GameState state, Player player) {
		return true;
	}

	@Override
	public Ability[] CardsToPlay(GameState state, Player player) {
		int currentBP = player.GetBattlePoints(state);
        if (currentBP >= 50)
            return null; 

        ArrayList<AdventureCard> validCards = new ArrayList<>();
        ArrayList<String> usedCards = new ArrayList<>();

        for (AdventureCard card : player.getPlayersCards())
        {
            if (player.CanPlayCardToBoard(card))
            {
                boolean used = false;
                for (int i = 0; i < usedCards.size() && !used; i++)
                    if (usedCards.get(i).equals(card.getName()))
                        used = true;
                if (!used)
                {
                    usedCards.add(card.getName());
                    validCards.add(card);
                }
            }
        }

        validCards.sort(new Comparator<AdventureCard>() {
			@Override
			public int compare(AdventureCard card1, AdventureCard card2) {
				if (card1.getBattlePoints(state) < card2.getBattlePoints(state))
	                return 1;
	            else if (card1.getBattlePoints(state) > card2.getBattlePoints(state))
	                return -1;
	            return 0;
			}
			});

        ArrayList<Ability> abilities = new ArrayList<>();
        for (AdventureCard card : validCards)
        {
            abilities.add(card.GetAbilities()[0]);
            currentBP += card.getBattlePoints(state);
            if (currentBP >= 50)
                break;
        }

        return abilities.toArray(new Ability[abilities.size()]);
	}

}

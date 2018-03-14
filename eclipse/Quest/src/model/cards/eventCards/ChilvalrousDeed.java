package model.cards.eventCards;

import model.cards.EventCard;
import model.game.GameState;
import model.player.Player;
import model.player.Rank;

public class ChilvalrousDeed extends EventCard {

	public ChilvalrousDeed() {
		super("Chivalrous Deed");
	}

    public void doEffect(GameState gState)
    {
    	Player[] players = gState.getPlayersInRankOrder();
		Rank lowest = new Rank(players[players.length - 1].GetRank().getCurrentRank(), players[players.length - 1].GetRank().getCurrentShields());
		
		for (int i = players.length - 1; i >= 0; i--) {
			if (players[i].GetRank().getCurrentRank() == lowest.getCurrentRank() && players[i].GetRank().getCurrentShields() == lowest.getCurrentShields())
				players[i].AddShields(3);
		}
    }

}

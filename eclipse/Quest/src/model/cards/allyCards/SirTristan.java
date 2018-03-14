package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;
import model.player.Player;

public class SirTristan extends AllyCard {

	public SirTristan() {
		super("Sir Tristan", 10, 0); //base 10
	}

    public int getBattlePoints(GameState gState) //override 20
    {
        //Player[] players = gState.getPlayers();
        for (Player player : gState.getPlayers())
        {
            if (player.getPlayersBoard().equals("Queen Iseult"))
            {
                return battlePoints = 20;
            }
            return battlePoints = 10;
        }
        return battlePoints = 10;
    }
    
}

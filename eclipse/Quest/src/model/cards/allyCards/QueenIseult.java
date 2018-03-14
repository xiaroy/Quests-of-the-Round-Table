package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;
import model.player.Player;

public class QueenIseult extends AllyCard{

	//private static final String Player = null;

	public QueenIseult() {
		super("Queen Iseult", 0, 2); //base 10
	}

    public int getBattlePoints(GameState gState) //override 20
    {
        //Player[] players = gState.getPlayers();
        for (Player player : gState.getPlayers())
        {
            if (player.getPlayersBoard().equals("Sir Tristan"))
            {
                return bidPower = 4;
            }
            return bidPower = 2;
        }
        return bidPower = 2;
    }
}

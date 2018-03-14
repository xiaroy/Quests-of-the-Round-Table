package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;

public class SirLancelot extends AllyCard {

	public SirLancelot() {
		super("Sir Lancelot", 15, 0); //base 5
	}

    public int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().equals("Defend the Queen's Honor"))
        {
            return battlePoints = 25;
        }
        return battlePoints = 15;
    }
}

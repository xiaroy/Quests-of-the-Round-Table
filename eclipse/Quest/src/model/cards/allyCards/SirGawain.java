package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;

public class SirGawain extends AllyCard {

	public SirGawain() {
		super("Sir Gawain", 10, 0); //base 10
	}

    public int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().equals("Test of the Green Knight"))
        {
            return battlePoints = 20;
        }
        return battlePoints = 10;
    }
}

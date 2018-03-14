package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;

public class SirPercival extends AllyCard {

	public SirPercival() {
		super("Sir Percival", 5, 0); //base 5
	}

    public int getBattlePoints(GameState gState) //override 20
    {
        if (gState.getCurrentStoryCard().getName().equals("Search for the Holy Grail"))
        {
            return battlePoints = 20;
        }
        return battlePoints = 5;
    }
    
}

package model.cards.allyCards;

import model.cards.AllyCard;
import model.game.GameState;

public class SirPellinore extends AllyCard {
	
	public SirPellinore() {
		super("King Pellinore", 10, 0); //base 10
	}

    public int getBidPower(GameState gState) //override 5
    {
        if (gState.getCurrentStoryCard().getName().equals("Search for the Question Beast"))
        {
            return bidPower = 4;
        }
        return bidPower = 0;
    }
}

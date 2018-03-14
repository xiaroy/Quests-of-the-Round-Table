package model.cards.eventCards;

import model.cards.AdventureCard;
import model.cards.EventCard;
import model.game.GameState;
import model.player.Player;

public class ProsperityEvent extends EventCard {

	public ProsperityEvent() {
		super("Prosperity Throughout the Realm");
	}

    public void doEffect(GameState gState)
    {
        //Each player draws 2 cards
        for (Player player : gState.getPlayers())
        {
            for (int i = 0; i < 2; i++)
                player.AddCardToHand((AdventureCard)gState.getAdventureDeck().Draw());
        }
    }
    
}

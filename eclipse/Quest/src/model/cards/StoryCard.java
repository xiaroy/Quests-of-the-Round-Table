package model.cards;

import model.game.GameState;

public abstract class StoryCard extends Card {

	public StoryCard(String name, CardTypes type){
		super(name, type);
	}

    /// <summary>
    /// Uses the effect of this card on the given game state that is passed in
    /// </summary>
    /// <param name="gState">The game state to use this cards effect on</param>
    public abstract void doEffect(GameState gState);
    
}

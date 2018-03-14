package model.cards;

import model.cards.abilities.Ability;
import model.game.GameState;

public abstract class AdventureCard extends Card {
	
	protected int battlePoints;
	protected int bidPower;

	public AdventureCard(String name, CardTypes type, int battlePoints, int bidPower) {
		super(name, type);
		this.battlePoints = battlePoints;
		this.bidPower = bidPower;
	}
	
	public AdventureCard(String name, CardTypes type, int battlePoints) {
		this(name, type, battlePoints, 0);
	}

	/// <summary>
    /// Gets the amount of points this card is worth when used in battle
    /// </summary>
    /// <returns>The number of points thsi card is worth when used in battle</returns>
    public int getBattlePoints(GameState gState) { return battlePoints; }
    /// <summary>
    /// Gets the amount of cards this card is worth when bidding
    /// </summary>
    /// <param name="gState">The game state to use this cards effect on</param>
    /// <returns>The amount of cards this card is worth when bidding</returns>
    public int getBidPower(GameState gState) { return bidPower; }

    /// <summary>
    /// Gets any abilities that this card has
    /// </summary>
    /// <returns>All of this cards special abilities, returns null if it has no abilities</returns>
    public abstract Ability[] GetAbilities();
}

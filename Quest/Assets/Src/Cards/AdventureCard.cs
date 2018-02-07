using UnityEngine;

public abstract class AdventureCard : Card
{

    protected int battlePoints;
    protected int bidPower;

    public AdventureCard(string name, CardTypes type, int battlePoints, int bidPower = 1) : base(name, type)
    {
        this.battlePoints = battlePoints;
        this.bidPower = bidPower;
    }

    /// <summary>
    /// Gets the amount of points this card is worth when used in battle
    /// </summary>
    /// <returns>The number of points thsi card is worth when used in battle</returns>
    public int getBattlePoints() { return battlePoints; }
    /// <summary>
    /// Gets the amount of cards this card is worth when bidding
    /// </summary>
    /// <returns>The amount of cards this card is worth when bidding</returns>
    public int getBidPower() { return bidPower; }
    /// <summary>
    /// Gets if this card has any special effects
    /// </summary>
    /// <returns>True if this card has any special effects</returns>
    public abstract bool hasEffect();

    /// <summary>
    /// Uses any special effects this card has on the given game state
    /// </summary>
    /// <param name="gState">The game state to use this cards effect on</param>
    public abstract void doEffect(GameState gState);
}
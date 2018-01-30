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

    public int getBattlePoints() { return battlePoints; }
    public int getBidPower() { return bidPower; }
    public abstract bool hasEffect();

    public abstract void doEffect(/*GameState gState*/);
}
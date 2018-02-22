using System;

public class AIPlayer : Player
{
    public int i;
    public string str;

	public AIPlayer(string name):base(name)
	{
	}
    public AIPlayer(string name, Ranks rank, int shield, Hand hand) : base(name, rank, shield, hand)
    {

    }

    public override bool playCard(Card card)
    {
        throw new NotImplementedException();
    }
}

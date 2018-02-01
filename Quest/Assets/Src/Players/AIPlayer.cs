using System;

public class AIPlayer : Player
{
    public int i;
    public string str;

	public AIPlayer(string name):base(name)
	{
	}
    public AIPlayer(string name, int rank, int shield, Hand hand) : base(name, rank, shield, hand)
    {

    }

    public override bool playCard(Hand hand)
    {
        throw new NotImplementedException();
    }
}

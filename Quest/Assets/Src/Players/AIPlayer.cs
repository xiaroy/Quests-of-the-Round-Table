using System;

public class AIPlayer : Player
{
    public int i;
    public string str;

	public AIPlayer(string name):base(name)
	{
	}

    public override bool playCard(Hand hand)
    {
        throw new NotImplementedException();
    }
}

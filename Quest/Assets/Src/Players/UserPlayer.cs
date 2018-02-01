using System;

public class UserPlayer : Player
{
    public UserPlayer(string name):base(name)
	{
        
	}
    public UserPlayer(string name, int rank, int shield, Hand hand):base(name, rank, shield, hand)
    {

    }

    public override bool playCard(Hand hand)
    {
        throw new NotImplementedException();
    }
}

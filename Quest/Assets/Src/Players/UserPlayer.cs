using System;

public class UserPlayer : Player
{
    public UserPlayer(string name):base(name)
	{
        
	}

    public override bool playCard(Hand hand)
    {
        throw new NotImplementedException();
    }
}

using System;

public class UserPlayer : Player
{
    public UserPlayer(string name):base(name)
	{
        
	}
    public UserPlayer(string name, Ranks rank, int shield, Hand hand):base(name, rank, shield, hand)
    {

    }
}

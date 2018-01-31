using System;

public class AIPlayer : Player
{
    public int i;
    public string str;

	public AIPlayer() : base("AI")
	{
        
	}

    public override bool playCard(Hand hand)
    {
        /*will randomly select cards for which AI will play
         * */
        return null;
    }

}

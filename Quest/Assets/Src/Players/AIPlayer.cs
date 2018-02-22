using System;
using System.Collections.Generic;
using System.Linq;

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

    public List<Card> CardsToPlayInTournament() //Should be in AIPlayer instead?
    {
        AdventureCard[] cardsInHand = hand.GetCards();
        cardsInHand = cardsInHand.OrderByDescending(x => x.getBattlePoints(null)).ToArray();
        List<Card> cardsToPlay = new List<Card>();
        int currBattlePoints = 0;
        foreach (AdventureCard card in cardsInHand)
        {
            currBattlePoints += card.getBattlePoints(null);
            //Strategy 2 
            if (currBattlePoints >= 50)
            {
                cardsToPlay.Add(card);
                break;
            }
            else
                cardsToPlay.Add(card);
        }
        return cardsToPlay;
    }
}

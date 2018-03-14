package model.player;

import java.util.ArrayList;

import model.cards.AdventureCard;
import model.cards.Card.CardTypes;
import model.game.CardCriteria;
import model.game.CardSpace;
import model.game.GameState;
import model.game.GameState.GameTime;

public class Board implements CardSpace<AdventureCard> {
	
	private ArrayList<AdventureCard> cardsInPlay = new ArrayList<>();

    public boolean AddCard(AdventureCard card)
    {
    	if (CanAddCard(card))
        {
            cardsInPlay.add(card);
            return true;
        }
        return false;
    }
    
    public boolean CanAddCard(AdventureCard card)
    {
        for (AdventureCard c : cardsInPlay)
        {
            if (card.getName().equals(c.getName()))
                return false;
        }
        return true;
    }

    public boolean ContainsCard(AdventureCard card)
    {
        return cardsInPlay.contains(card);
    }

    public AdventureCard[] GetCards()
    {
        return cardsInPlay.toArray(new AdventureCard[cardsInPlay.size()]);
    }

    public boolean RemoveCard(AdventureCard card)
    {
        return cardsInPlay.remove(card);
    }

    public int TotalCards()
    {
        return cardsInPlay.size();
    }

    public int getBoardBattlePoints(GameState state)
    {
        int totalBP = 0;
        for (AdventureCard card : cardsInPlay)
            if (!(state.getCurrentGameTime() == GameTime.InTournament && card.GetCardType() == CardTypes.Ally))
                totalBP += card.getBattlePoints(state);
        return totalBP;
    }

    public int getBoardFreeBids(GameState state)
    {
        int total = 0;
        for (AdventureCard card : cardsInPlay)
            total += card.getBidPower(state);
        return total;
    }

    public void RemoveAllCardsWithCriteria(CardCriteria criteria)
    {
    	ArrayList<AdventureCard> remove = new ArrayList<>();
        for (AdventureCard card : cardsInPlay)
            if (criteria.DoesMeetCriteria(card))
            	remove.add(card);
        cardsInPlay.removeAll(remove);
    }
}

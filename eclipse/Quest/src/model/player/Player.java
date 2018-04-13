package model.player;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.cards.AdventureCard;
import model.game.CardCriteria;
import model.game.GameState;
import model.player.Rank.Ranks;

public class Player {

	protected String name;
    protected Rank rank;
    protected Hand hand;
    protected Board board;

    /*creating a new Player
     * input : name (string) - name of player
     * */
    public Player(String name)
    {
        this.name = name;
        rank = new Rank();
        board = new Board();
        hand = new Hand();
    }
    /*constructor to create a player from given attributes
     * would be good for test cases
     * */
    public Player(String name, Ranks rank, int shields, Hand hand)
    {
        this.name = name;
        this.rank = new Rank(rank, shields);
        this.hand = hand;
        board = new Board();
    }
    //getters
    public String GetName() { return name; }

    public AdventureCard[] getPlayersCards() { return hand.GetCards(); }

    public AdventureCard[] getPlayersBoard() { return board.GetCards(); }

    //setters
    public void SetName(String name) { this.name = name; }

    public void AddShields(int shields) { rank.AddShields(shields); }

    public Rank GetRank() { return rank; }

    public boolean CanPlayCardToBoard(AdventureCard card) { return board.CanAddCard(card); }
    
    public boolean AddCardToHand(AdventureCard card) { 
    	boolean success = hand.AddCard(card); 
    	if (success) Logger.getLogger(Player.class.getName()).log(Level.FINE, name + " recieved card: " + card.getName());
    	else Logger.getLogger(Player.class.getName()).log(Level.FINE, name + " failed to recieve card: " + card.getName());
    	return success; 
    }
    public boolean AddCardToBoard(AdventureCard card) { return board.AddCard(card); }
    public boolean PlayCardFromHandToBoard(AdventureCard card)
    {
        if (hand.ContainsCard(card))
        {
            if (board.AddCard(card))
            {
                hand.RemoveCard(card);
                return true;
            }
        }
        return false;
    }

    public boolean HandContains(AdventureCard card) { return hand.ContainsCard(card); }
    public boolean BoardContains(AdventureCard card) { return board.ContainsCard(card); }

    public boolean RemoveCardFromHand(AdventureCard card) { return hand.RemoveCard(card); }
    public boolean RemoveCardFromBoard(AdventureCard card) { return board.RemoveCard(card); }
    
    public void RemoveCardsFromBoardWithCriteria(CardCriteria criteria) { board.RemoveAllCardsWithCriteria(criteria); }

    public int GetBattlePoints(GameState state)
    {
        return board.getBoardBattlePoints(state) + rank.getRankBattlePoints();
    }

    public int GetMaximumBids(GameState state)
    {
        return board.getBoardFreeBids(state) + hand.GetTotalBids(state);
    }
    
    public String toString() {
    	String str = name + "\n";
    	str += "\tRank: " + rank.getCurrentRank() + ", " + rank.getCurrentShields() + "\n";
    	str += "\tHand: ";
    	for (AdventureCard card : hand.GetCards())
    		str += card.getName() + ", ";
    	str += "\n\tBoard: ";
    	for (AdventureCard card : board.GetCards())
    		str += card.getName() + ", ";
		return str;
    }
}

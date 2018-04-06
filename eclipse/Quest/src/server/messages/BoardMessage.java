package server.messages;

import view.GameView;

public class BoardMessage {

	private String name;
	private String rank;
	private int sheilds;
	private CardMessage[] hand;
	private CardMessage[] board;
	
	private OpponentMessage[] opponents;
	
	public class OpponentMessage {
		private String name;
		private String rank;
		private int sheilds;
		private int hand;
		private CardMessage[] board;
		
		public void setName(String name) { this.name = name; }
		public String getName() { return name; }
		
		public void setRank(String rank) { this.rank = rank; }
		public String getRank() { return rank; }
		
		public void setSheilds(int shields) { this.sheilds = shields; }
		public int getSheilds() { return sheilds; }
		
		public void setHand(int hand) { this.hand = hand; }
		public int getHand() { return hand; }
		
		public void setBoard(CardMessage[] board) { this.board = board; }
		public CardMessage[] getBoard() { return board; }
	}
	
	public void createMessage(GameView view) { 
		name = view.GetPerspectiveName();
		rank = view.GetPerspectiveRank().getCurrentRank().toString();
		sheilds = view.GetPerspectiveRank().getCurrentShields();
		hand = new CardMessage[view.GetPerspectiveHand().length];
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new CardMessage();
			hand[i].setCard(view.GetPerspectiveHand()[i]);
		}
		board = new CardMessage[view.GetPerspectiveBoard().length];
		for (int i = 0; i < board.length; i++) {
			board[i] = new CardMessage();
			board[i].setCard(view.GetPerspectiveBoard()[i]);
		}
		
		opponents = new OpponentMessage[view.NumberOfOtherPlayers()];
		for (int i = 0; i < view.NumberOfOtherPlayers(); i++) {
			opponents[i] = new OpponentMessage();
			opponents[i].setName(view.GetOtherPlayerName(i));
			opponents[i].setRank(view.GetOtherPlayerRank(i).getCurrentRank().toString());
			opponents[i].setSheilds(view.GetOtherPlayerRank(i).getCurrentShields());
			opponents[i].setHand(view.GetOtherPlayerHandCount(i));
			
			CardMessage[] oppBoard = new CardMessage[view.GetOtherPlayerBoard(i).length];
			for (int j = 0; j < oppBoard.length; j++) {
				oppBoard[j] = new CardMessage();
				oppBoard[j].setCard(view.GetOtherPlayerBoard(i)[j]);
			}
			opponents[i].setBoard(oppBoard);
		}
	}
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
	public void setRank(String rank) { this.rank = rank; }
	public String getRank() { return rank; }
	
	public void setSheilds(int shields) { this.sheilds = shields; }
	public int getSheilds() { return sheilds; }
	
	public void setHand(CardMessage[] hand) { this.hand = hand; }
	public CardMessage[] getHand() { return hand; }
	
	public void setBoard(CardMessage[] board) { this.board = board; }
	public CardMessage[] getBoard() { return board; }
	
	public void setOpponents(OpponentMessage[] opponents) { this.opponents = opponents; }
	public OpponentMessage[] getOpponents() { return opponents; }
	
}

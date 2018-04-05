package test.spring;

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
	
	public class CardMessage {
		private String name;
		private String address;
		
		public void setName(String name) { this.name = name; }
		public String getName() { return name; }
		
		public void setAddress(String address) { this.address = address; }
		public String getAddress() { return address; }
	}
	
	public void createMessage(GameView view) { 
		name = view.GetPerspectiveName();
		rank = view.GetPerspectiveRank().getCurrentRank().toString();
		sheilds = view.GetPerspectiveRank().getCurrentShields();
		hand = new CardMessage[view.GetPerspectiveHand().length];
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new CardMessage();
			hand[i].setName(view.GetPerspectiveHand()[i].getName());
			hand[i].setAddress("img/" + view.GetPerspectiveHand()[i].GetCardType() + "/" + view.GetPerspectiveHand()[i].getName() + ".png");
		}
		board = new CardMessage[view.GetPerspectiveBoard().length];
		for (int i = 0; i < hand.length; i++) {
			board[i] = new CardMessage();
			board[i].setName(view.GetPerspectiveBoard()[i].getName());
			board[i].setAddress("img/" + view.GetPerspectiveBoard()[i].GetCardType() + "/" + view.GetPerspectiveBoard()[i].getName() + ".png");
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
				oppBoard[j].setName(view.GetOtherPlayerBoard(i)[j].getName());
				oppBoard[j].setAddress("img/" + view.GetOtherPlayerBoard(i)[j].GetCardType() + "/" + view.GetOtherPlayerBoard(i)[j].getName() + ".png");
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

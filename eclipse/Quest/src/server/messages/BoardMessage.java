package server.messages;

import view.GameView;

public class BoardMessage {

	private String name;
	private String rank;
	private int sheilds;
	private CardMessage[] hand;
	private CardMessage[] board;
	
	private OpponentMessage[] opponents;
	
	private CardMessage storyCard;
	private CardMessage questCards[][];
	
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
			hand[i].setIndex(i);
		}
		board = new CardMessage[view.GetPerspectiveBoard().length];
		for (int i = 0; i < board.length; i++) {
			board[i] = new CardMessage();
			board[i].setCard(view.GetPerspectiveBoard()[i]);
			board[i].setIndex(i);
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
				oppBoard[j].setIndex(j);
			}
			opponents[i].setBoard(oppBoard);
		}
		
		if (view.GetCurrentStory() != null) {
			storyCard = new CardMessage();
			storyCard.setCard(view.GetCurrentStory());
		}
		else storyCard = null;
		
		if (view.getCurrentQuest() != null) {
			questCards = new CardMessage[view.getCurrentQuest().GetNumberOfStages()][];
			for (int i = 0; i < questCards.length; i++) {
				questCards[i] = new CardMessage[view.getCurrentQuest().GetCardsForStage(i).length];
				for (int j = 0; j < questCards[i].length; j++) {
					questCards[i][j] = new CardMessage();
					questCards[i][j].setCard(view.getCurrentQuest().GetCardsForStage(i)[j]);
				}
			}
		}
		else questCards = null;
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
	
	public void setStoryCard(CardMessage storyCard) { this.storyCard = storyCard; }
	public CardMessage getStoryCard() { return storyCard; }
	
	public void setQuestCards(CardMessage[][] questCards) { this.questCards = questCards; }
	public CardMessage[][] getQuestCards() { return questCards; }
}

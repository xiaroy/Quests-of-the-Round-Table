package model.game;

import java.util.ArrayList;

import model.cards.AdventureCard;

public class QuestStage {

	private QuestInfo quest;
	private int stage;
	
	ArrayList<AdventureCard> cards = new ArrayList<>();
	
	public QuestStage(QuestInfo info, int stage) {
		this.quest = info;
		this.stage = stage;
	}
	
	
	public void addCard(AdventureCard card) {
		cards.add(card);
	}
	
	public int getBattlePoints(GameState state) {
		int total = 0;
		for (AdventureCard card : cards)
			total += card.getBattlePoints(state);
		return total;
	}
	
	public int getStageNum() { return stage; }
	
	public AdventureCard[] getCards() { return cards.toArray(new AdventureCard[cards.size()]); }
}

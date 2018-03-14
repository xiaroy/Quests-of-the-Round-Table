package test.playertests;

import junit.framework.TestCase;
import model.cards.AdventureCard;
import model.cards.Card;
import model.cards.Card.CardTypes;
import model.cards.WeaponCard;
import model.cards.WeaponCard.WeaponTypes;
import model.cards.allyCards.*;
import model.game.CardCriteria;
import model.player.Board;

public class BoardTest extends TestCase {

	public void testAdd() {
		Board board = new Board();
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		board.AddCard(new WeaponCard(WeaponTypes.Horse));
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		
		assertEquals(2, board.GetCards().length);
	}
	
	public void testRemove() {
		Board board = new Board();
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		board.AddCard(new WeaponCard(WeaponTypes.Horse));
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		
		board.RemoveCard(board.GetCards()[1]);
		
		assertEquals(1, board.GetCards().length);
	}
	
	public void testCardsInHand() {
		Board board = new Board();
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		board.AddCard(new WeaponCard(WeaponTypes.Horse));
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		board.AddCard(new WeaponCard(WeaponTypes.Excalibur));
		
		board.RemoveCard(board.GetCards()[2]);
		
		int other = 0, dagger = 0, horse = 0;
		for (AdventureCard card : board.GetCards()) {
			if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Dagger)))
				dagger++;
			else if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Horse)))
				horse++;
			else other++;
		}
		
		assertEquals(0, other);
		assertEquals(1, dagger);
		assertEquals(1, horse);
	}
	
	public void testRemoveCriteria() {
		Board board = new Board();
		board.AddCard(new WeaponCard(WeaponTypes.Dagger));
		board.AddCard(new WeaponCard(WeaponTypes.Horse));
		board.AddCard(new WeaponCard(WeaponTypes.Excalibur));
		board.AddCard(new KingArthur());
		board.AddCard(new Merlin());
		
		board.RemoveAllCardsWithCriteria(new CardCriteria() {
			@Override
			public boolean DoesMeetCriteria(Card card) {
				return card.GetCardType() != CardTypes.Ally;
			}
		});
		
		int other = 0, excalibur = 0, dagger = 0, horse = 0;
		for (AdventureCard card : board.GetCards()) {
			if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Dagger)))
				dagger++;
			else if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Horse)))
				horse++;
			else if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Excalibur)))
				excalibur++;
			else other++;
		}
		
		assertEquals(2, other);
		assertEquals(0, dagger);
		assertEquals(0, horse);
		assertEquals(0, excalibur);
	}
	
}

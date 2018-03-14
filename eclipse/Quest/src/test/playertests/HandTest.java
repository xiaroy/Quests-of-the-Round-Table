package test.playertests;

import junit.framework.TestCase;
import model.cards.AdventureCard;
import model.cards.WeaponCard;
import model.cards.WeaponCard.WeaponTypes;
import model.player.Hand;

public class HandTest extends TestCase {

	public void testAdd() {
		Hand hand = new Hand();
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		hand.AddCard(new WeaponCard(WeaponTypes.Horse));
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		
		assertEquals(3, hand.GetCards().length);
	}
	
	public void testRemove() {
		Hand hand = new Hand();
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		hand.AddCard(new WeaponCard(WeaponTypes.Horse));
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		
		hand.RemoveCard(hand.GetCards()[1]);
		
		assertEquals(2, hand.GetCards().length);
	}
	
	public void testCardsInHand() {
		Hand hand = new Hand();
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		hand.AddCard(new WeaponCard(WeaponTypes.Horse));
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		hand.AddCard(new WeaponCard(WeaponTypes.Dagger));
		
		hand.RemoveCard(hand.GetCards()[2]);
		
		int other = 0, dagger = 0, horse = 0;
		for (AdventureCard card : hand.GetCards()) {
			if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Dagger)))
				dagger++;
			else if (card.getName().equals(WeaponCard.getWeaponName(WeaponTypes.Horse)))
				horse++;
			else other++;
		}
		
		assertEquals(0, other);
		assertEquals(2, dagger);
		assertEquals(1, horse);
	}
	
}

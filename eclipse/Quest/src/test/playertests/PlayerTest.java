package test.playertests;

import junit.framework.TestCase;
import model.cards.WeaponCard;
import model.cards.WeaponCard.WeaponTypes;
import model.player.Player;

public class PlayerTest extends TestCase {
	
	public void testPlayCardFromHandToBoard() {
		Player player = new Player("Tester");
		player.AddCardToHand(new WeaponCard(WeaponTypes.Horse));
		player.AddCardToHand(new WeaponCard(WeaponTypes.BattleAx));
		player.AddCardToBoard(new WeaponCard(WeaponTypes.BattleAx));
		
		player.PlayCardFromHandToBoard(player.getPlayersCards()[0]);
		
		assertEquals(2, player.getPlayersBoard().length);
		assertEquals(1, player.getPlayersCards().length);
	}

}

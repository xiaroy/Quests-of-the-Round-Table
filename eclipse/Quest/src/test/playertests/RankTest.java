package test.playertests;

import junit.framework.TestCase;
import model.player.Rank;
import model.player.Rank.Ranks;

public class RankTest extends TestCase {

	public void testAddSheild() {
		Rank rank = new Rank(Ranks.Squire, 2);
		rank.AddShields(1);
		assertEquals(true, rank.getCurrentRank() == Ranks.Squire && rank.getCurrentShields() == 3);
	}
	
	public void testSquireRankUp() {
		Rank rank = new Rank(Ranks.Squire, 2);
		rank.AddShields(4);
		assertEquals(true, rank.getCurrentRank() == Ranks.Knight && rank.getCurrentShields() == 1);
	}
	
	public void testKnightRankUp() {
		Rank rank = new Rank(Ranks.Knight, 5);
		rank.AddShields(5);
		assertEquals(true, rank.getCurrentRank() == Ranks.ChampionKnight && rank.getCurrentShields() == 3);
	}
	
	public void testChampKnightRankUp() {
		Rank rank = new Rank(Ranks.ChampionKnight, 2);
		rank.AddShields(11);
		assertEquals(true, rank.getCurrentRank() == Ranks.KnightOfRoundTable && rank.getCurrentShields() == 3);
	}
	
	public void testSquireRankDown() {
		Rank rank = new Rank(Ranks.Squire, 2);
		rank.AddShields(-4);
		assertEquals(true, rank.getCurrentRank() == Ranks.Squire && rank.getCurrentShields() == 0);
	}
	
	public void testKnightRankDown() {
		Rank rank = new Rank(Ranks.Knight, 5);
		rank.AddShields(-7);
		assertEquals(true, rank.getCurrentRank() == Ranks.Squire && rank.getCurrentShields() == 3);
	}
	
	public void testChampKnightRankDown() {
		Rank rank = new Rank(Ranks.ChampionKnight, 2);
		rank.AddShields(-4);
		assertEquals(true, rank.getCurrentRank() == Ranks.Knight && rank.getCurrentShields() == 5);
	}
	
}

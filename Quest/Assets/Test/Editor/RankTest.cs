using UnityEngine;
using UnityEditor;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;

public class RankTest {

	[Test]
	public void RankTestSimplePasses() {
        Rank rank = new Rank();

        Assert.AreEqual(true, rank.getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, rank.getCurrentShields());
    }

    [Test]
    public void SquireRankUpTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.Squire, 2);
        rank.AddShields(4);

        Assert.AreEqual(Ranks.Knight, rank.getCurrentRank());
        Assert.AreEqual(1, rank.getCurrentShields());
    }

    [Test]
    public void KnightRankUpTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.Knight, 5);
        rank.AddShields(4);

        Assert.AreEqual(Ranks.ChampionKnight, rank.getCurrentRank());
        Assert.AreEqual(2, rank.getCurrentShields());
    }

    [Test]
    public void ChampKnightRankUpTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.ChampionKnight, 7);
        rank.AddShields(5);

        Assert.AreEqual(Ranks.KnightOfRoundTable, rank.getCurrentRank());
        Assert.AreEqual(2, rank.getCurrentShields());
    }

    [Test]
    public void SquireRankDownTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.Squire, 2);
        rank.AddShields(-4);

        Assert.AreEqual(Ranks.Squire, rank.getCurrentRank());
        Assert.AreEqual(0, rank.getCurrentShields());
    }

    [Test]
    public void KnightRankDownTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.Knight, 3);
        rank.AddShields(-4);

        Assert.AreEqual(Ranks.Squire, rank.getCurrentRank());
        Assert.AreEqual(4, rank.getCurrentShields());
    }

    [Test]
    public void ChampKnightRankDownTestSimplePasses()
    {
        Rank rank = new Rank(Ranks.ChampionKnight, 3);
        rank.AddShields(-5);

        Assert.AreEqual(Ranks.Knight, rank.getCurrentRank());
        Assert.AreEqual(5, rank.getCurrentShields());
    }
}

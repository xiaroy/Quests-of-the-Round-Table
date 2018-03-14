package model.player;

public class Rank {

	private int shields = 0;
    Ranks curRank = Ranks.Squire;

    public Rank() { }
    public Rank(Ranks curRank, int shields)
    {
        this.curRank = curRank;
        this.shields = shields;
        CheckRankUp();
    }

    /// <summary>
    /// Adds the given number of shields to this rank
    /// </summary>
    /// <param name="newShields">The number of shields to add to this rank</param>
    public void AddShields(int newShields)
    {
        shields += newShields;
        CheckRankUp();
    }

    /// <summary>
    /// Gets the current Rank
    /// </summary>
    /// <returns>The Current Rank contained in this class</returns>
    public Ranks getCurrentRank() { return curRank; }
    /// <summary>
    /// Gets the number of shields in this rank
    /// </summary>
    /// <returns>The number of shields that are in this rank</returns>
    public int getCurrentShields() { return shields; }

    /// <summary>
    /// Gets the number of Battle points this Rank gives you
    /// </summary>
    /// <returns>The number of battle points this rank gives you</returns>
    public int getRankBattlePoints()
    {
        switch (curRank)
        {
            case Squire:
                return 5;
            case Knight:
                return 10;
            case ChampionKnight:
                return 20;
        }
        return 0;
    }

    private void CheckRankUp()
    {
        if (shields < 0)
        {
            switch (curRank)
            {
                case KnightOfRoundTable:
                    curRank = Ranks.ChampionKnight;
                    shields += 10;
                    break;
                case ChampionKnight:
                    curRank = Ranks.Knight;
                    shields += 7;
                    break;
                case Knight:
                    curRank = Ranks.Squire;
                    shields += 5;
                    break;
                case Squire:
                    shields = 0;
                    break;
            }
        }
        if (curRank == Ranks.Squire && shields >= 5)
        {
            shields -= 5;
            curRank = Ranks.Knight;
        }
        else if (curRank == Ranks.Knight && shields >= 7)
        {
            shields -= 7;
            curRank = Ranks.ChampionKnight;
        }
        else if (curRank == Ranks.ChampionKnight && shields >= 10)
        {
            shields -= 10;
            curRank = Ranks.KnightOfRoundTable;
        }
    }
    
    public boolean greaterThan(Rank other)
    {
        if (this.getCurrentRank().valueOf() > other.getCurrentRank().valueOf())
            return true;
        else if (this.getCurrentShields() > other.getCurrentShields())
            return true;
        return false;
    }
    
    public enum Ranks
    {
        Squire,
        Knight,
        ChampionKnight,
        KnightOfRoundTable;
        
        public int valueOf() {
        	if (this == Squire)
        		return 0;
        	else if (this == Knight)
        		return 1;
        	else if (this == ChampionKnight)
        		return 2;
        	else if (this == KnightOfRoundTable)
        		return 3;
        	return -1;
        }
    }
}

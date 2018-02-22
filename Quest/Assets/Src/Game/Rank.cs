using System.Collections;
using System.Collections.Generic;
using UnityEngine;

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

    public void AddShields(int newShields)
    {
        shields += newShields;
        CheckRankUp();
    }

    public Ranks getCurrentRank() { return curRank; }
    public int getCurrentShields() { return shields; }

    private void CheckRankUp()
    {
        if (shields < 0)
        {
            switch (curRank)
            {
                case Ranks.KnightOfRoundTable:
                    curRank = Ranks.ChampionKnight;
                    shields += 10;
                    break;
                case Ranks.ChampionKnight:
                    curRank = Ranks.Knight;
                    shields += 7;
                    break;
                case Ranks.Knight:
                    curRank = Ranks.Squire;
                    shields += 5;
                    break;
                case Ranks.Squire:
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

    public static bool operator >(Rank r1, Rank r2)
    {
        if (r1.getCurrentRank() <= r2.getCurrentRank())
            return false;
        else if (r1.getCurrentShields() <= r2.getCurrentShields())
            return false;
        return true;
    }

    public static bool operator <(Rank r1, Rank r2)
    {
        if (r1.getCurrentRank() >= r2.getCurrentRank())
            return false;
        else if (r1.getCurrentShields() >= r2.getCurrentShields())
            return false;
        return true;
    }

    public static bool operator ==(Rank r1, Rank r2)
    {
        return (r1.getCurrentRank() == r2.getCurrentRank() && r1.getCurrentShields() == r2.getCurrentShields());
    }

    public static bool operator !=(Rank r1, Rank r2)
    {
        return !(r1.getCurrentRank() == r2.getCurrentRank() && r1.getCurrentShields() == r2.getCurrentShields());
    }
}



public enum Ranks
{
    Squire,
    Knight,
    ChampionKnight,
    KnightOfRoundTable
}

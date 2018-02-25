using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestCard : AdventureCard
{
    private int minBids = 0;

    public TestCard(TestTypes type) : base(GetTestName(type), CardTypes.Test, 0)
    {
        minBids = GetTestMinBids(type);
    }

    public int getMinBids() { return minBids; }

    public override Ability[] GetAbilities()
    {
        return new Ability[] { new TestAbility(this) };
    }

    public static string GetTestName(TestTypes type)
    {
        switch (type)
        {
            case TestTypes.QuestingBeast:
                return "Test of the Questing Beast";
            case TestTypes.Temptation:
                return "Test of Temptation";
            case TestTypes.Valor:
                return "Test of Valor";
            case TestTypes.MorganLeFey:
                return "Test of Morgan Le Fey";
        }
        return null;
    }

    private static int GetTestMinBids(TestTypes type)
    {
        switch (type)
        {
            case TestTypes.QuestingBeast:
                return 4;
            case TestTypes.MorganLeFey:
                return 3;
            default:
                return 3;
        }
    }
}

public enum TestTypes
{
    QuestingBeast,
    Valor,
    Temptation,
    MorganLeFey,
}

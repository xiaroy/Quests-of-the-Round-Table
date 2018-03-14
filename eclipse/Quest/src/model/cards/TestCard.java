package model.cards;

import model.cards.abilities.Ability;
import model.cards.abilities.TestAbility;

public class TestCard extends AdventureCard {

	private int minBids = 0;

    public TestCard(TestTypes type)
    {
    	super(GetTestName(type), CardTypes.Test, 0);
        this.minBids = GetTestMinBids(type);
    }

    public int getMinBids() { return minBids; }

    public Ability[] GetAbilities()
    {
        return new Ability[] { new TestAbility(this) };
    }

    public static String GetTestName(TestTypes type)
    {
        switch (type)
        {
            case QuestingBeast:
                return "Test of the Questing Beast";
            case Temptation:
                return "Test of Temptation";
            case Valor:
                return "Test of Valor";
            case MorganLeFey:
                return "Test of Morgan Le Fey";
        }
        return null;
    }

    private static int GetTestMinBids(TestTypes type)
    {
        switch (type)
        {
            case QuestingBeast:
                return 4;
            case MorganLeFey:
                return 3;
            default:
                return 3;
        }
    }
    
    public enum TestTypes
    {
        QuestingBeast,
        Valor,
        Temptation,
        MorganLeFey,
    }
}

package model.cards;

import model.cards.abilities.Ability;
import model.cards.abilities.FoeAbility;
import model.game.GameState;

public class FoeCard extends AdventureCard {

	
	protected int highBattlePoint;
	
    public FoeCard(FoeTypes type)
    {
    	super(getFoeName(type), CardTypes.Foe, getLowBattlePoints(type));
        highBattlePoint = getHighBattlePoints(type);
    }

    public int getBattlePoints(GameState gState)
    {
        if (gState.getCurrentStoryCard().GetCardType() == CardTypes.Quest)
        {
            QuestCard curQuest = (QuestCard)gState.getCurrentStoryCard();
            for (int i = 0; i < curQuest.getSpecialEnemy().length; i++)
            {
                if (curQuest.getSpecialEnemy()[i].equals(this.getName()))
                return highBattlePoint;
            }
        }
        return battlePoints;
    }

    public Ability[] GetAbilities()
    {
        return new Ability[] { new FoeAbility(this) };
    }

    public static String getFoeName(FoeTypes name)
    {
        switch (name)
        {
            case RobberKnight:
                return "Robber Knight";
            case Saxons:
                return "Saxons";
            case Boar:
                return "Boar";
            case Thieves:
                return "Thieves";
            case GreenKnight:
                return "Green Knight";
            case BlackKnight:
                return "Black Knight";
            case EvilKnight:
                return "Evil Knight";
            case SaxonKnight:
                return "Saxon Knight";
            case Dragon:
                return "Dragon";
            case Giant:
                return "Giant";
            case Mordred:
                return "Mordred";
        }
        return null;
    }

    public static int getLowBattlePoints(FoeTypes LowBattlePoints)
    {
        switch (LowBattlePoints)
        {
            case RobberKnight:
                return 15;
            case Saxons:
                return 10;
            case Boar:
                return 5;
            case Thieves:
                return 5;
            case GreenKnight:
                return 25;
            case BlackKnight:
                return 25;
            case EvilKnight:
                return 20;
            case SaxonKnight:
                return 15;
            case Dragon:
                return 50;
            case Giant:
                return 40;
            case Mordred:
                return 30;
        }
        return 0;
    }

    public static int getHighBattlePoints(FoeTypes HighBattlePoints)
    {
        switch (HighBattlePoints)
        {
            case RobberKnight:
                return 15;
            case Saxons:
                return 20;
            case Boar:
                return 15;
            case Thieves:
                return 5;
            case GreenKnight:
                return 40;
            case BlackKnight:
                return 35;
            case EvilKnight:
                return 30;
            case SaxonKnight:
                return 25;
            case Dragon:
                return 70;
            case Giant:
                return 40;
            case Mordred:
                return 30;
        }
        return 0;
    }
    
    public enum FoeTypes
    {
        RobberKnight,
        Saxons,
        Boar,
        Thieves,
        GreenKnight,
        BlackKnight,
        EvilKnight,
        SaxonKnight,
        Dragon,
        Giant,
        Mordred,
    }
}

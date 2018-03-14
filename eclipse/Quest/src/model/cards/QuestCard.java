package model.cards;

import model.cards.FoeCard.FoeTypes;
import model.game.GameState;

public class QuestCard extends StoryCard{

	protected int reward;
    protected int stages;
    protected String[] specialEnemy;

    public QuestCard(QuestTypes type) {
    	super(getQuestName(type), CardTypes.Quest);
        this.stages = getQuestStages(type);
        this.reward = stages;
        this.specialEnemy = getQuestSpecialEnemies(type);
    }

    public int getReward() { return reward; }
    public int getStages() { return stages; }
    public String[] getSpecialEnemy() { return specialEnemy; }

    public void doEffect(GameState gState)
    {
        gState.startQuest(this);
    }
    
    public static String getQuestName(QuestTypes type) {
    	switch (type) {
	    	case BoarHunt:
	    		return "Boar Hunt";
	    	case DefendQueenHonor:
	    		return "Defend the Queen's Honor";
	    	case EnchantedForest:
	    		return "Journey through the Enchanted Forest";
	    	case RepelSaxons:
	    		return "Repel the Saxon Raiders";
	    	case RescueFairMaiden:
	    		return "Rescue the Fair Maiden";
	    	case HolyGrail:
	    		return "Search for the Holy Grail";
	    	case QuestingBeast:
	    		return "Search for the Questing Beast";
	    	case SlayDragon:
	    		return "Slay the Dragon";
	    	case GreenKnight:
	    		return "Test of the Green Knight";
	    	case VanquishArhtursEnemies:
	    		return "Vanquish King Arthur's Enemies";
    	}
    	return "Unknown";
    }
    
    private static int getQuestStages(QuestTypes type) {
    	switch (type) {
	    	case BoarHunt:
	    	case RepelSaxons:
	    		return 2;
	    	case EnchantedForest:
	    	case RescueFairMaiden:
	    	case SlayDragon:
	    	case VanquishArhtursEnemies:
	    		return 3;
	    	case DefendQueenHonor:
	    	case QuestingBeast:
	    	case GreenKnight:
	    		return 4;
	    	case HolyGrail:
	    		return 5;
		}
		return 0;
    }
    
    private static String[] getQuestSpecialEnemies(QuestTypes type) {
    	String[] enemies;
    	int i = 0;
    	
    	switch (type) {
	    	case BoarHunt:
	    		return new String[] {FoeCard.getFoeName(FoeTypes.Boar)};
	    	case DefendQueenHonor:
	    	case HolyGrail:
	    		enemies = new String[FoeTypes.values().length];
	    		i = 0;
	    		for (FoeTypes fType : FoeTypes.values()) 
	    			enemies[i++] = FoeCard.getFoeName(fType);
	    		return enemies;
	    	case RepelSaxons:
	    		enemies = new String[FoeTypes.values().length];
	    		i = 0;
	    		for (FoeTypes fType : FoeTypes.values()) 
	    			if (FoeCard.getFoeName(fType).contains("Saxon"))
	    				enemies[i++] = FoeCard.getFoeName(fType);
	    		return enemies;
	    	case EnchantedForest:
	    		return new String[] {FoeCard.getFoeName(FoeTypes.EvilKnight)};
	    	case RescueFairMaiden:
	    		return new String[] {FoeCard.getFoeName(FoeTypes.BlackKnight)};
	    	case SlayDragon:
	    		return new String[] {FoeCard.getFoeName(FoeTypes.Dragon)};
	    	case GreenKnight:
	    		return new String[] {FoeCard.getFoeName(FoeTypes.GreenKnight)};
    		default:
    			return new String[0];
		}
    }
    
    public enum QuestTypes{
    	BoarHunt,
    	DefendQueenHonor,
    	EnchantedForest,
    	RepelSaxons,
    	RescueFairMaiden,
    	HolyGrail,
    	QuestingBeast,
    	SlayDragon,
    	GreenKnight,
    	VanquishArhtursEnemies;
    }
}

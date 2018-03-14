package model.cards;

public abstract class Card {
	
	protected String name;
    protected CardTypes type;
    
    public Card(String name, CardTypes type) {
    	this.name = name;
    	this.type = type;
    }
    
    public String getName() { return name; }
    
    public CardTypes GetCardType() { return type; }
	
    public static enum CardTypes{
    	Event,
        Quest,
        Tournament,
        Weapon,
        Amour,
        Ally,
        Foe,
        Test,
    }
}

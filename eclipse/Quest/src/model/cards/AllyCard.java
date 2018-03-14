package model.cards;

import model.cards.abilities.Ability;
import model.cards.abilities.AllyAbility;

public abstract class AllyCard extends AdventureCard {

	public AllyCard(String name, int battlePoints, int bidPower) {
		super(name, CardTypes.Ally, battlePoints, bidPower);
	}
	
	public AllyCard(String name, int battlePoints) {
		this(name, battlePoints, 0);
	}
	
	public Ability[] GetAbilities() {
		return new Ability[] { new AllyAbility(this) };
	}
	
}

package model.cards;

import model.cards.abilities.Ability;
import model.cards.abilities.AmourAbility;

public class AmourCard extends AdventureCard {

	public AmourCard() {
		super("Amour", CardTypes.Amour, 10, 1);
	}

    public Ability[] GetAbilities()
    {
        return new Ability[] { new AmourAbility(this) };
    }
}

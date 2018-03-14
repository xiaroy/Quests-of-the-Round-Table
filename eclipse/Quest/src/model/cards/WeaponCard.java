package model.cards;

import model.cards.abilities.Ability;
import model.cards.abilities.WeaponAbility;

public class WeaponCard extends AdventureCard{

	public WeaponCard(WeaponTypes type) {
		super(getWeaponName(type), CardTypes.Weapon, getBattlePoints(type));
	}

    public Ability[] GetAbilities()
    {
        return new Ability[] { new WeaponAbility(this) };
    }

    public static String getWeaponName(WeaponTypes type)
    {
        switch (type)
        {
            case Dagger:
                return "Dagger";
            case Sword:
                return "Sword";
            case Horse:
                return "Horse";
            case BattleAx:
                return "Battle-Ax";
            case Lance:
                return "Lance";
            case Excalibur:
                return "Excalibur";
        }
        return null;
    }

    private static int getBattlePoints(WeaponTypes type)
    {
        switch (type)
        {
            case Dagger:
                return 5;
            case Sword:
                return 10;
            case Horse:
                return 10;
            case BattleAx:
                return 15;
            case Lance:
                return 20;
            case Excalibur:
                return 30;
        }
        return 0;
    }
    
    public enum WeaponTypes
    {
        Dagger,
        Sword,
        Horse,
        BattleAx,
        Lance,
        Excalibur,
    }
	
}

using UnityEngine;
using System.Collections;

public class WeaponCard : AdventureCard
{

    public WeaponCard(WeaponTypes type) : base(getWeaponName(type), CardTypes.Weapon, getBattlePoints(type)) { }

    public override Ability[] GetAbilities()
    {
        return new Ability[] { new WeaponAbility(this) };
    }

    public static string getWeaponName(WeaponTypes type)
    {
        switch (type)
        {
            case WeaponTypes.Dagger:
                return "Dagger";
            case WeaponTypes.Sword:
                return "Sword";
            case WeaponTypes.Horse:
                return "Horse";
            case WeaponTypes.BattleAx:
                return "Battle Axe";
            case WeaponTypes.Lance:
                return "Lance";
            case WeaponTypes.Excalibur:
                return "Excalibur";
        }
        return null;
    }

    private static int getBattlePoints(WeaponTypes type)
    {
        switch (type)
        {
            case WeaponTypes.Dagger:
                return 5;
            case WeaponTypes.Sword:
                return 10;
            case WeaponTypes.Horse:
                return 10;
            case WeaponTypes.BattleAx:
                return 15;
            case WeaponTypes.Lance:
                return 20;
            case WeaponTypes.Excalibur:
                return 30;
        }
        return 0;
    }

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

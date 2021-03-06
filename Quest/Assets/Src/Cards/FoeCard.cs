﻿using UnityEngine;
using System.Collections;



public class FoeCard : AdventureCard
{
    protected int highBattlePoint;

    public FoeCard(FoeTypes type) : base(getFoeName(type), CardTypes.Foe, getLowBattlePoints(type))
    {
        highBattlePoint = getHighBattlePoints(type);
    }

    public override int getBattlePoints(GameState gState)
    {
        if (gState.getCurrentStoryCard().GetCardType() == CardTypes.Quest)
        {
            QuestCard curQuest = (QuestCard)gState.getCurrentStoryCard();
            for (int i = 0; i < curQuest.getSpecialEnemy().Length; i++)
            {
                if (curQuest.getSpecialEnemy()[i].Equals(this.getName()))
                return highBattlePoint;
            }
        }
        return battlePoints;
    }

    public override Ability[] GetAbilities()
    {
        return new Ability[] { new FoeAbility(this) };
    }

    public static string getFoeName(FoeTypes name)
    {
        switch (name)
        {
            case FoeTypes.RobberKnight:
                return "Robber Knight";
            case FoeTypes.Saxons:
                return "Saxons";
            case FoeTypes.Boar:
                return "Boar";
            case FoeTypes.Thieves:
                return "Thieves";
            case FoeTypes.GreenKnight:
                return "Green Knight";
            case FoeTypes.BlackKnight:
                return "Black Knight";
            case FoeTypes.EvilKnight:
                return "Evil Knight";
            case FoeTypes.SaxonKnight:
                return "Saxon Knight";
            case FoeTypes.Dragon:
                return "Dragon";
            case FoeTypes.Giant:
                return "Giant";
            case FoeTypes.Mordred:
                return "Mordred";
        }
        return null;
    }

    public static int getLowBattlePoints(FoeTypes LowBattlePoints)
    {
        switch (LowBattlePoints)
        {
            case FoeTypes.RobberKnight:
                return 15;
            case FoeTypes.Saxons:
                return 10;
            case FoeTypes.Boar:
                return 5;
            case FoeTypes.Thieves:
                return 5;
            case FoeTypes.GreenKnight:
                return 25;
            case FoeTypes.BlackKnight:
                return 25;
            case FoeTypes.EvilKnight:
                return 20;
            case FoeTypes.SaxonKnight:
                return 15;
            case FoeTypes.Dragon:
                return 50;
            case FoeTypes.Giant:
                return 40;
            case FoeTypes.Mordred:
                return 30;
        }
        return 0;
    }

    public static int getHighBattlePoints(FoeTypes HighBattlePoints)
    {
        switch (HighBattlePoints)
        {
            case FoeTypes.RobberKnight:
                return 15;
            case FoeTypes.Saxons:
                return 20;
            case FoeTypes.Boar:
                return 15;
            case FoeTypes.Thieves:
                return 5;
            case FoeTypes.GreenKnight:
                return 40;
            case FoeTypes.BlackKnight:
                return 35;
            case FoeTypes.EvilKnight:
                return 30;
            case FoeTypes.SaxonKnight:
                return 25;
            case FoeTypes.Dragon:
                return 70;
            case FoeTypes.Giant:
                return 40;
            case FoeTypes.Mordred:
                return 30;
        }
        return 0;
    }
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
﻿using UnityEngine;
using System.Collections;

[CreateAssetMenu(fileName = "New Quest Card", menuName = "Quest Card")]
public class QuestCard : StoryCard
{
    protected int reward;
    protected int stages;
    protected string specialEnemy;

    public QuestCard(string name, int stages, string specialEnemy = null) : base(name, CardTypes.Quest)
    {
        this.stages = stages;
        this.reward = stages;
        this.specialEnemy = specialEnemy;
    }

    public int getReward() { return reward; }
    public int getStages() { return stages; }
    public string getSpecialEnemy() { return specialEnemy; }

    public override void doEffect(GameState gState)
    {
        gState.startQuest(this);
    }
}

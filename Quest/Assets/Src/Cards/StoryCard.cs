using UnityEngine;
using System.Collections;

public abstract class StoryCard : Card
{

    public StoryCard(string name, CardTypes type) : base(name, type) { }

    public abstract void doEffect(GameState gState);
}

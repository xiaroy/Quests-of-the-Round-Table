using UnityEngine;
using System.Collections;

public abstract class StoryCard : Card
{

    public StoryCard(string name, CardTypes type) : base(name, type) { }

    /// <summary>
    /// Uses the effect of this card on the given game state that is passed in
    /// </summary>
    /// <param name="gState">The game state to use this cards effect on</param>
    public abstract void doEffect(GameState gState);
}

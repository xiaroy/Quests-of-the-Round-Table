using UnityEngine;
using System.Collections;

[CreateAssetMenu(fileName = "New Event Card", menuName = "Event Card")]
public class EventCard : StoryCard
{

    public EventCard(string name) : base(name, CardTypes.Event) { }

    public override void doEffect(GameState gState)
    {
        throw new System.NotImplementedException();
    }
}

using UnityEngine;
using System.Collections;

public abstract class EventCard : StoryCard
{

    public EventCard(string name) : base(name, CardTypes.Event) { }
}

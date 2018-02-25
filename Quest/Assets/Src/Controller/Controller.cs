using UnityEngine;
using System.Collections;

public abstract class Controller
{
    protected Player player;
    protected ControllerHub hub;

    public Controller(Player player, ControllerHub hub)
    {
        this.player = player;
        this.hub = hub;
    }

    public abstract ControllerResponse PromptForInput(GameState state, ControllerMessageType type);
}

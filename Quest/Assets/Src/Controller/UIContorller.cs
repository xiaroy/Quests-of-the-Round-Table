using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UIController : Controller {

    public UIController(Player player, ControllerHub hub): base(player, hub) { }

    public override ControllerResponse PromptForInput(GameState state, ControllerMessageType type)
    {
        return ControllerResponse.Continue;
    }
}

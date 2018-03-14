package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.game.GameState;
import model.player.Player;
import view.GameView;

public abstract class Controller {
	protected Player player;
    protected ControllerHub hub;

    public Controller(Player player, ControllerHub hub)
    {
        this.player = player;
        this.hub = hub;
    }

    public abstract ControllerResponse PromptForInput(GameState state, ControllerMessageType type);
    
    public GameView getPlayerView() { return hub.GetPlayerPerspective(player); }
}

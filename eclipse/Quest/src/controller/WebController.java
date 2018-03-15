package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.game.GameState;
import model.player.Player;

public class WebController extends Controller {

	public WebController(Player player, ControllerHub hub) {
		super(player, hub);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		// TODO Auto-generated method stub
		return null;
	}

}

package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;
import ui.gameboard.GameBoardPanel;
import view.GameView;

public class UIController extends Controller {
	
	private GameState state;
	private GameBoardPanel gameBoard;
	
	public UIController(Player player, ControllerHub hub, GameState state, GameBoardPanel gameBoard) {
		super(player, hub);
		this.state = state;
		this.gameBoard = gameBoard;
	}

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		return gameBoard.getInput(this, type);
	}
	
	public boolean canAbilityTarget(Ability ability, Object target) {
		return ability.CanTarget(state, target);
	}
	
	public boolean doesAbilityTarget(Ability ability) {
		return ability.DoesTarget(state);
	}
	
	public void setAbilityTarget(Ability ability, Object target) {
		ability.SetTarget(state, target);
	}
	
	public boolean canUseAbility(Ability ability) {
		return ability.CanUseAbility(state, player);
	}
	
	public void useAbility(Ability ability) {
		ability.UseAbility(state, player);
	}

	public GameView getPlayerView() {
		return hub.GetPlayerPerspective(player);
	}
	
}

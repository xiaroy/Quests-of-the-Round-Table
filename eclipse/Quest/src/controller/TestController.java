package controller;

import java.util.ArrayDeque;
import java.util.Deque;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.Card;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;

public class TestController extends Controller {

	Deque<TestCommand> testCommands = new ArrayDeque<>();
	
	public TestController(Player player, ControllerHub hub) {
		super(player, hub);
	}

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		TestCommand curCommand = testCommands.pop();
		curCommand.doCommand(player, hub, state);
		return curCommand.getResponse(player, hub, state);
	}
	
	@Override
	public void updateDisplay() {}
	
	@Override
	public void sendSystemMessage(String msg) {}
	
	public void addCommand(TestCommand command) {
		testCommands.add(command);
	}
	
	public static interface TestCommand {
		public void doCommand(Player player, ControllerHub hub, GameState state);
		public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state);
	}

}

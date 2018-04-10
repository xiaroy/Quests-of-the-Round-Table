package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.AdventureCard;
import model.game.GameState;
import model.player.Player;
import server.SpringServer;
import server.messages.InputMessage;
import ui.gameboard.ControlPanel;

public class WebController extends Controller {
	
	private SpringServer server;
	private int inputSelected = -1;

	public WebController(Player player, ControllerHub hub, SpringServer server) {
		super(player, hub);
		this.server = server;
	}

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		InputMessage inputMsg = new InputMessage();
		inputMsg.setUser(player.GetName());
		inputSelected = -1;
		
		switch (type){
		case WillSponsor:
			inputMsg.setMessage("Do you wish to Sponor this quest");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case CreateQuestStages:
			inputMsg.setMessage("Select the Cards to use in the Quest");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case ParticipateInQuest:
			inputMsg.setMessage("Do you wish to Participate this Quest");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case PlayForQuest:
			inputMsg.setMessage("Select the Cards to use in the Quest");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case BidForTest:
			inputMsg.setMessage("How many Cards do you want to bid?");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case ParticipateInTournament:
			inputMsg.setMessage("Do you wish to Participate this Tournament");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case PlayForTournament:
			inputMsg.setMessage("Select the Cards to use in the Tournament");
			inputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		}
		
		server.sendInput(inputMsg);
		
		while (inputSelected < 0 || inputSelected >= inputMsg.getOptions().length) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return stringToResponse(inputMsg.getOptions()[inputSelected]);
	}
	
	public void selectedInput(int selected) {
		System.out.println(player.GetName() + " : " + selected);
		inputSelected = selected;
	}
	
	public void playCard(String name, int index) {
		System.out.println(player.GetName() + " : " + name + " : " + index);
		for (AdventureCard card : player.getPlayersCards()) {
			
		}
	}
	
	@Override
	public void updateDisplay() {
		server.sendBoard(hub.GetPlayerPerspective(player));
	}
	
	private String[] getControlOptions(ControlPanel.ControlTypes type) {
		switch (type){
		case YesNo:
			return new String[] {responseToString(ControllerResponse.Yes), responseToString(ControllerResponse.No)};
		case Continue:
			return new String[] {responseToString(ControllerResponse.Continue)};
		}
		return null;
	}
	
	private String responseToString(ControllerResponse type) {
		switch (type) {
		case Yes:
			return "Yes";
		case No:
			return "No";
		case Continue:
			return "Continue";
		case Back:
			return "Back";
		}
		return null;
	}
	
	private ControllerResponse stringToResponse(String str) {
		switch (str) {
		case "Yes":
			return ControllerResponse.Yes;
		case "No":
			return  ControllerResponse.No;
		case "Continue":
			return  ControllerResponse.Continue;
		case "Back":
			return  ControllerResponse.Back;
		}
		return null;
	}

}

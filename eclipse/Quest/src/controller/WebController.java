package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.AdventureCard;
import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;
import server.SpringServer;
import server.messages.ChatMessage;
import server.messages.ChatMessage.MessageType;
import server.messages.InputMessage;
import ui.gameboard.ControlPanel;

public class WebController extends Controller {
	
	private SpringServer server;
	private int inputSelected = -1;
	private InputMessage curInputMsg = null;
	private boolean targetting = false;
	private GameState state = null;
	private AdventureCard lookingForTarget = null;

	public WebController(Player player, ControllerHub hub, SpringServer server) {
		super(player, hub);
		this.server = server;
	}

	@Override
	public ControllerResponse PromptForInput(GameState state, ControllerMessageType type) {
		this.state = state;
		curInputMsg = new InputMessage();
		curInputMsg.setUser(player.GetName());
		inputSelected = -1;
		
		switch (type){
		case WillSponsor:
			curInputMsg.setMessage("Do you wish to Sponor this quest");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case CreateQuestStages:
			curInputMsg.setMessage("Select the Cards to use in the Quest");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case ParticipateInQuest:
			curInputMsg.setMessage("Do you wish to Participate this Quest");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case PlayForQuest:
			curInputMsg.setMessage("Select the Cards to use in the Quest");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case BidForTest:
			curInputMsg.setMessage("How many Cards do you want to bid?");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case ParticipateInTournament:
			curInputMsg.setMessage("Do you wish to Participate this Tournament");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.YesNo));
			break;
		case PlayForTournament:
			curInputMsg.setMessage("Select the Cards to use in the Tournament");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		case Continue:
			curInputMsg.setMessage("Continue");
			curInputMsg.setOptions(getControlOptions(ControlPanel.ControlTypes.Continue));
			break;
		}
		
		server.sendInput(curInputMsg);
		
		while (inputSelected < 0 || inputSelected >= curInputMsg.getOptions().length) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ControllerResponse rep = stringToResponse(curInputMsg.getOptions()[inputSelected]);
		curInputMsg = null;
		InputMessage waitMsg = new InputMessage();
		waitMsg.setUser(player.GetName());
		waitMsg.setMessage("Waiting");
		waitMsg.setOptions(new String[0]);
		server.sendInput(waitMsg);
		return rep;
	}
	
	public void selectedInput(InputMessage msg) {
		System.out.println(player.GetName() + " : " + msg.getMessage() + " : " + msg.getSelected());
		if (msg.getMessage().equals("QuestStage")) {
			if (targetting && state.getCurrentQuest() != null) {
				if (lookingForTarget.GetAbilities()[0].CanTarget(state, state.getCurrentQuest().getStage(msg.getSelected()))) {
					Ability a = lookingForTarget.GetAbilities()[0];
					a.SetTarget(state, state.getCurrentQuest().getStage(msg.getSelected()));
					hub.UseCardAbilities(player, new Ability[] {a});
					updateDisplay();
				}
			}
		}
		else {
			if (!targetting) //selected option
				inputSelected = msg.getSelected();
			else { //hit back
				server.sendInput(curInputMsg);
				targetting = false;
				lookingForTarget = null;
			}
		}
	}
	
	public void playCard(String name, int index) {
		System.out.println(player.GetName() + " : " + name + " : " + index);
		if (state == null)
			return;
		
		AdventureCard card = null;
		if (index < player.getPlayersCards().length && player.getPlayersCards()[index].getName().equals(name))
			card = player.getPlayersCards()[index];
		else {
			for (AdventureCard c : player.getPlayersCards()) {
				if (c.getName().equals(name)) {
					card = c;
					break;
				}
			}
		}
		
		if (card == null)
			return;
		
		if (targetting) {
			if (lookingForTarget.GetAbilities()[0].CanTarget(state, card)) {
				Ability a[] = new Ability[1];
				a[0] = lookingForTarget.GetAbilities()[0];
				a[0].SetTarget(state, card);
				hub.UseCardAbilities(player, a);
				targetting = false;
				lookingForTarget = null;
				updateDisplay();
			}
		}
		else {
			if (!card.GetAbilities()[0].CanUseAbility(state, player))
				return;
			
			if (card.GetAbilities()[0].DoesTarget(state)) {
				targetting = true;
				lookingForTarget = card;
				InputMessage targetMsg = new InputMessage();
				targetMsg.setMessage("Select a target for " + card.getName());
				targetMsg.setOptions(new String[] {responseToString(ControllerResponse.Back)});
			}
			else {
				Ability a[] = new Ability[1];
				a[0] = card.GetAbilities()[0];
				hub.UseCardAbilities(player, a);
				updateDisplay();
			}
		}
	}
	
	@Override
	public void updateDisplay() {
		server.sendBoard(hub.GetPlayerPerspective(player));
	}
	
	@Override
	public void sendSystemMessage(String msg) {
		ChatMessage chatMsg = new ChatMessage();
		chatMsg.setType(MessageType.GAME);
		chatMsg.setContent(msg);
		server.sendMessage(player.GetName(), chatMsg);
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

package controller;

import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import model.cards.abilities.Ability;
import model.player.Player;
import ui.gameboard.GameBoardPanel;

public class HotSeatHub extends ControllerHub {
	
	private GameBoardPanel view;
	
	public void setBoardPanel(GameBoardPanel panel) {
    	view = panel;
    }

	@Override
	public ControllerResponse[] PromptUserInput(Player[] players, ControllerMessageType msg)
    {
    	ControllerResponse[] response = new ControllerResponse[players.length];
        int i = 0;
        for (Player player : players)
        {
            response[i] = controllers.get(player).PromptForInput(gState, msg);
            i++;
        }
        return response;
    }

	@Override
    public void updateView() {
    	if (view == null)
    		return;
    	view.updateView();
    	view.hideHand();
    }
    
	@Override
    public void sendUIMessage(String msg) {
    	if (view == null)
    		return;
    	view.sendGeneralMessage(null, msg);
    }
    
	@Override
    public void sendUIMessage(Player playerView, String msg) {
    	if (view == null)
    		return;
    	view.sendGeneralMessage(GetPlayerPerspective(playerView), msg);
    }
}

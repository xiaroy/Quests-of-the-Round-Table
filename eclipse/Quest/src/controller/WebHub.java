package controller;

import controller.ControllerHub.ControllerResponse;
import model.player.Player;

public class WebHub extends ControllerHub {

	@Override
	public ControllerResponse[] PromptUserInput(Player[] players, ControllerMessageType msg) {
		ControllerResponse[] response = new ControllerResponse[players.length];
		Thread inputThreads[] = new Thread[players.length];
        int i = 0;
        for (Player player : players)
        {
        	final int curPlayerRepNum = i;
        	final Player curPlayer = player;
        	inputThreads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
		            response[curPlayerRepNum] = controllers.get(curPlayer).PromptForInput(gState, msg);
				}
			});
        	inputThreads[i].start();
            i++;
        }
        
        for (i = 0; i < inputThreads.length; i++) {
        	try {
				inputThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        return response;
	}

	@Override
	public void updateView() {
		for (Player p : controllers.keySet()) {
			controllers.get(p).updateDisplay();
		}
	}

	@Override
	public void sendUIMessage(String msg) {
		for (Player p : controllers.keySet()) {
			controllers.get(p).sendSystemMessage(msg);
		}
	}

	@Override
	public void sendUIMessage(Player playerView, String msg) {
		Controller c = controllers.get(playerView);
		if (c != null)
			c.sendSystemMessage(msg);
	}

}

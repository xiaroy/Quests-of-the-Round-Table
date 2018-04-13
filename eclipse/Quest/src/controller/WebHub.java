package controller;

import java.util.HashMap;

import controller.ControllerHub.ControllerResponse;
import model.player.Player;
import view.GameView;

public class WebHub extends ControllerHub {
	
	protected HashMap<Player, GameView> views = new HashMap<>();

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
			views.put(p, new GameView(gState, p));
			controllers.get(p).updateDisplay();
		}
	}
	
	@Override
	public GameView GetPlayerPerspective(Player player)
    {
        return views.get(player);
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

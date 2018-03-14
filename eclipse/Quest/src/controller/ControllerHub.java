package controller;

import java.util.HashMap;

import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;
import ui.gameboard.GameBoardPanel;
import view.GameView;

public class ControllerHub {

	private GameState gState;
	private GameBoardPanel view;

    private HashMap<Player, Controller> controllers = new HashMap<>();

    public void SetGameState(GameState gState)
    {
        this.gState = gState;
    }
    
    public void setControllerForPlayer(Player p, Controller c) {
    	controllers.put(p, c);
    }
    
    public void setBoardPanel(GameBoardPanel panel) {
    	view = panel;
    }

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

    public boolean CanUseCardAbility(Player source, Ability ability)
    {
        return gState.CanUseAbilityNow(source, ability);
    }

    public void UseCardAbilities(Player source, Ability[] abilities)
    {
        gState.UseAbilities(source, abilities);
    }

    public void updateView() {
    	if (view == null)
    		return;
    	view.updateView();
    	view.hideHand();
    }
    
    public void sendUIMessage(String msg) {
    	if (view == null)
    		return;
    	view.sendGeneralMessage(null, msg);
    }
    
    public void sendUIMessage(Player playerView, String msg) {
    	if (view == null)
    		return;
    	view.sendGeneralMessage(GetPlayerPerspective(playerView), msg);
    }
    
    public GameView GetPlayerPerspective(Player player)
    {
        return new GameView(gState, player);
    }
    
    public GameView GetPerspective() {
    	return new GameView(gState, gState.getCurrentTurnPlayer());
    }
    
    public enum ControllerMessageType
    {
        WillSponsor,
        CreateQuestStages,
        ParticipateInQuest,
        PlayForQuest,
        BidForTest,
        ParticipateInTournament,
        PlayForTournament,
    }
    
    public enum ControllerResponse{
    	Yes,
    	No,
    	Continue,
    	Back;
    }
}

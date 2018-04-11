package controller;

import java.util.HashMap;

import model.cards.abilities.Ability;
import model.game.GameState;
import model.player.Player;
import ui.gameboard.GameBoardPanel;
import view.GameView;

public abstract class ControllerHub {

	protected GameState gState;

    protected HashMap<Player, Controller> controllers = new HashMap<>();

    public void SetGameState(GameState gState)
    {
        this.gState = gState;
    }
    
    public void setControllerForPlayer(Player p, Controller c) {
    	controllers.put(p, c);
    }

    public abstract ControllerResponse[] PromptUserInput(Player[] players, ControllerMessageType msg);

    public boolean CanUseCardAbility(Player source, Ability ability)
    {
        return gState.CanUseAbilityNow(source, ability);
    }

    public void UseCardAbilities(Player source, Ability[] abilities)
    {
        gState.UseAbilities(source, abilities);
    }

    public abstract void updateView();
    
    public abstract void sendUIMessage(String msg);
    
    public abstract void sendUIMessage(Player playerView, String msg);
    
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

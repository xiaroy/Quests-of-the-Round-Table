using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;

public class ControllerHub {

    private GameState gState;

    private Dictionary<Player, bool> inputRecieved = new Dictionary<Player, bool>();
    private Dictionary<Player, Controller> controllers = new Dictionary<Player, Controller>();

    public void SetGameState(GameState gState)
    {
        this.gState = gState;
    }

    public void setControllerForPlayer(Player p, Controller c)
    {
        controllers.Add(p, c);
    }

    public ControllerResponse[] PromptUserInput(Player[] players, ControllerMessageType msg)
    {
        ControllerResponse[] response = new ControllerResponse[players.Length];
        int i = 0;
        foreach (Player player in players)
        {
            response[i] = controllers[player].PromptForInput(gState, msg);
            i++;
        }
        return response;
    }

    public void InputRecivedForPlayer(Player player)
    {
        inputRecieved[player] = true;
    }

    public bool CanUseCardAbility(Player source, Ability ability)
    {
        return gState.CanUseAbilityNow(source, ability);
    }

    public void UseCardAbilities(Player source, Ability[] abilities)
    {
        gState.UseAbilities(source, abilities);
    }

    public GameView GetPlayerPerspective(Player player)
    {
        return new GameView(gState, player);
    }
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

public enum ControllerResponse
{
    Yes,
    No,
    Continue,
    Back,
}
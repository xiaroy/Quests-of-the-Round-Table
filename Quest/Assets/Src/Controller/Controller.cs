using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;

public abstract class Controller {

    private GameState gState;

    private Dictionary<Player, bool> inputRecieved = new Dictionary<Player, bool>();

    public void SetGameState(GameState gState)
    {
        this.gState = gState;
    }

    public bool[] PromptUserQuestion(Player[] players, string msg)
    {
        //return UI.promptForQuestion(Player, msg);
        bool[] response = new bool[players.Length];
        return response;
    }

    public void PromptUserToPlay(Player[] players, string msg)
    {
        foreach (Player player in players)
        {
            inputRecieved.Add(player, false);
            //UI.promptForInput(player, msg);
            while (!inputRecieved[player])
                Thread.Sleep(100);
        }
        inputRecieved.Clear();
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
}

public enum ControllerMessageType
{
    YESNO,
    CONTINUE,
}
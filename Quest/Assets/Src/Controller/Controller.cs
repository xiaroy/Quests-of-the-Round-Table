using System.Collections;
using System.Collections.Generic;
using System.Threading;
using UnityEngine;

public abstract class Controller {

    GameState gState;

    Dictionary<Player, bool> inputRecieved = new Dictionary<Player, bool>();

    public void PromptForInput(Player[] players, string msg, ControllerMessageType type)
    {
        foreach (Player player in players)
        {
            inputRecieved.Add(player, false);
            while (!inputRecieved[player])
                Thread.Sleep(100);
        }
        inputRecieved.Clear();
    }

    public void InputRecivedForPlayer(Player player)
    {
        inputRecieved[player] = true;
    }
}

public enum ControllerMessageType
{
    YESNO,
    CONTINUE,
}
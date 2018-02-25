using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class TestController : Controller
{

    Queue<TestCommand> testCommands = new Queue<TestCommand>();

    public TestController(Player player, ControllerHub hub) : base(player, hub) { }
    
    public override ControllerResponse PromptForInput(GameState state, ControllerMessageType type)
    {
        TestCommand curCommand = testCommands.Dequeue();
        curCommand.doCommand(player, hub, state);
        return curCommand.getResponse(player, hub, state);
    }

    public void addCommand(TestCommand command)
    {
        testCommands.Enqueue(command);
    }
    
}

public interface TestCommand
{
    void doCommand(Player player, ControllerHub hub, GameState state);
    ControllerResponse getResponse(Player player, ControllerHub hub, GameState state);
}

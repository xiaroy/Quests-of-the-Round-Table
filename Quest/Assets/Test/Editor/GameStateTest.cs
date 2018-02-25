using UnityEngine;
using UnityEditor;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;

public class GameStateTest {

    [Test]
    public void StartGameSimplePasses() {
        Deck storyDeck = new Deck();
        Deck adventureDeck = new Deck();
        ControllerHub hub = new ControllerHub();
        Player[] players = new Player[4];
        TestController[] con = new TestController[players.Length];
        for (int i = 0; i < players.Length; i++)
        {
            players[i] = new Player("Player " + i);
            con[i] = new TestController(players[i], hub);
            hub.setControllerForPlayer(players[i], con[i]);
        }
        GameState game = new GameState(players, adventureDeck, storyDeck);
        game.setController(hub);
        hub.SetGameState(game);

        storyDeck.AddCard(new BoarHunt());
        storyDeck.AddCard(new ProsperityEvent());
        for (int i = 0; i < 20; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.BattleAx));

        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Dagger));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Excalibur));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[1].AddCardToHand(new WeaponCard(WeaponTypes.BattleAx));
        players[2].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[2].AddCardToHand(new FoeCard(FoeTypes.Boar));
        players[2].AddCardToHand(new FoeCard(FoeTypes.EvilKnight));

        con[0].addCommand(new C1());
        con[1].addCommand(new C2());
        con[2].addCommand(new C3());
        con[2].addCommand(new C4());
        con[0].addCommand(new C5());
        con[1].addCommand(new C6());
        con[3].addCommand(new C7());
        con[0].addCommand(new C8());
        con[1].addCommand(new C9());
        con[0].addCommand(new C10());
        con[1].addCommand(new C11());

        game.startGame();

        foreach (Player p in game.getPlayers())
            Debug.Log(p.ToString());

        Assert.AreEqual(true, players[0].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(2, players[0].GetRank().getCurrentShields());
        Assert.AreEqual(6, players[0].getPlayersCards().Length);

        Assert.AreEqual(true, players[1].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[1].GetRank().getCurrentShields());
        Assert.AreEqual(3, players[1].getPlayersCards().Length);

        Assert.AreEqual(true, players[2].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[2].GetRank().getCurrentShields());
        Assert.AreEqual(7, players[2].getPlayersCards().Length);

        Assert.AreEqual(true, players[3].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[3].GetRank().getCurrentShields());
        Assert.AreEqual(2, players[3].getPlayersCards().Length);
    }

    [Test]
    public void StartQuestSimplePasses()
    {
        Deck storyDeck = new Deck();
        Deck adventureDeck = new Deck();
        ControllerHub hub = new ControllerHub();
        Player[] players = new Player[4];
        TestController[] con = new TestController[players.Length];
        for (int i = 0; i < players.Length; i++)
        {
            players[i] = new Player("Player " + i);
            con[i] = new TestController(players[i], hub);
            hub.setControllerForPlayer(players[i], con[i]);
        }
        GameState game = new GameState(players, adventureDeck, storyDeck);
        game.setController(hub);
        hub.SetGameState(game);

        for (int i = 0; i < 20; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.BattleAx));

        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Dagger));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Excalibur));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[1].AddCardToHand(new WeaponCard(WeaponTypes.BattleAx));
        players[2].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[2].AddCardToHand(new FoeCard(FoeTypes.Boar));
        players[2].AddCardToHand(new FoeCard(FoeTypes.EvilKnight));

        con[0].addCommand(new C1());
        con[1].addCommand(new C2());
        con[2].addCommand(new C3());
        con[2].addCommand(new C4());
        con[0].addCommand(new C5());
        con[1].addCommand(new C6());
        con[3].addCommand(new C7());
        con[0].addCommand(new C8());
        con[1].addCommand(new C9());
        con[0].addCommand(new C10());
        con[1].addCommand(new C11());

        game.startQuest(new BoarHunt());

        Assert.AreEqual(true, players[0].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(2, players[0].GetRank().getCurrentShields());
        Assert.AreEqual(4, players[0].getPlayersCards().Length);

        Assert.AreEqual(true, players[1].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[1].GetRank().getCurrentShields());
        Assert.AreEqual(1, players[1].getPlayersCards().Length);

        Assert.AreEqual(true, players[2].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[2].GetRank().getCurrentShields());
        Assert.AreEqual(5, players[2].getPlayersCards().Length);

        Assert.AreEqual(true, players[3].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(0, players[3].GetRank().getCurrentShields());
        Assert.AreEqual(0, players[3].getPlayersCards().Length);
    }

    class C1 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
    }
        
		
    class C2 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
    }

    class C3 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
    }

    class C4 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state)
        {
            Ability[] toDo = new Ability[3];
            toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
            toDo[0].SetTarget(state, state.getCurrentQuest().getStage(0));
            toDo[1] = player.getPlayersCards()[0].GetAbilities()[0];
            toDo[1].SetTarget(state, state.getCurrentQuest().getStage(1));
            toDo[2] = player.getPlayersCards()[2].GetAbilities()[0];
            toDo[2].SetTarget(state, state.getCurrentQuest().getStage(1));
            hub.UseCardAbilities(player, toDo);
            for (int i = 0; i < state.getCurrentQuest().GetNumberOfStages(); i++) {
                string str = "";
                str += ("Stage " + i);
                Debug.Log(state.getCurrentStoryCard());
                str += ("\n\tTotal BP: " + state.getCurrentQuest().GetBattlePointsForStage(i, state));
                str += ("\n\t");
                foreach (AdventureCard card in state.getCurrentQuest().GetCardsForStage(i))
                    str += (card.getName() + ", ");
                str += "\n";
                Debug.Log(str);
            }
        }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

    class C5 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
    }

    class C6 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
    }

    class C7 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
    }
    class C8 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state)
        {
            Ability[] toDo = new Ability[1];
            toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
            hub.UseCardAbilities(player, toDo);
        }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

    class C9 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state)
        {
            Ability[] toDo = new Ability[1];
            toDo[0] = player.getPlayersCards()[0].GetAbilities()[0];
            hub.UseCardAbilities(player, toDo);
        }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

    class C10 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

    class C11 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }
    
}

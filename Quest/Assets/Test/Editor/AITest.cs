using UnityEngine;
using UnityEditor;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;

public class AITest {

	[Test]
	public void AITestSimplePasses() {
        Deck storyDeck = new Deck();
        Deck adventureDeck = new Deck();
        ControllerHub hub = new ControllerHub();
        Player[] players = new Player[4];
        TestController[] con = new TestController[1];

        players[0] = new Player("Player 0");
        con[0] = new TestController(players[0], hub);
        hub.setControllerForPlayer(players[0], con[0]);
        players[1] = new Player("Player 1");
        AIController AIcon1 = new AIController(players[1], hub, new AISponorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
        hub.setControllerForPlayer(players[1], AIcon1);
        players[2] = new Player("Player 2");
        AIController AIcon2 = new AIController(players[2], hub, new AISponorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
        hub.setControllerForPlayer(players[2], AIcon2);
        players[3] = new Player("Player 3");
        AIController AIcon3 = new AIController(players[3], hub, new AISponorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
        hub.setControllerForPlayer(players[3], AIcon3);

        GameState game = new GameState(players, adventureDeck, storyDeck);
        game.setController(hub);
        hub.SetGameState(game);

        storyDeck.AddCard(new BoarHunt());
        for (int i = 0; i < 20; i++)
            adventureDeck.AddCard(new WeaponCard(WeaponTypes.Dagger));

        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Dagger));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Excalibur));
        players[0].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[1].AddCardToHand(new WeaponCard(WeaponTypes.BattleAx));
        players[1].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[1].AddCardToHand(new FoeCard(FoeTypes.Boar));
        players[1].AddCardToHand(new FoeCard(FoeTypes.Boar));
        players[2].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
        players[2].AddCardToHand(new FoeCard(FoeTypes.Boar));
        players[2].AddCardToHand(new FoeCard(FoeTypes.EvilKnight));

        con[0].addCommand(new C1());
        con[0].addCommand(new C2());
        con[0].addCommand(new C3());
        con[0].addCommand(new C4());

        game.startGame();

        Assert.AreEqual(true, players[0].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(2, players[0].GetRank().getCurrentShields());
        Assert.AreEqual(4, players[0].getPlayersCards().Length);

        Assert.AreEqual(true, players[1].GetRank().getCurrentRank() == Ranks.Squire);
        Assert.AreEqual(2, players[1].GetRank().getCurrentShields());
        Assert.AreEqual(4, players[1].getPlayersCards().Length);

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
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
    }

    class C3 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state)
        {
            Ability[] toDo = new Ability[1];
            toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
            hub.UseCardAbilities(player, toDo);
        }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

    class C4 : TestCommand
    {
        public void doCommand(Player player, ControllerHub hub, GameState state) { }
        public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
    }

}

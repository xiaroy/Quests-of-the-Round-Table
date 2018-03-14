package test.gametests;

import controller.ControllerHub;
import controller.TestController;
import controller.ControllerHub.ControllerResponse;
import controller.TestController.TestCommand;
import junit.framework.TestCase;
import model.cards.AdventureCard;
import model.cards.FoeCard;
import model.cards.QuestCard;
import model.cards.StoryCard;
import model.cards.WeaponCard;
import model.cards.FoeCard.FoeTypes;
import model.cards.WeaponCard.WeaponTypes;
import model.cards.abilities.Ability;
import model.cards.eventCards.ChilvalrousDeed;
import model.cards.eventCards.ProsperityEvent;
import model.game.Deck;
import model.game.GameState;
import model.player.Player;
import model.player.Rank.Ranks;

public class GameTest extends TestCase {

	public void testCoupleTurns() {
		Deck<StoryCard> storyDeck = new Deck<>();
		Deck<AdventureCard> adventureDeck = new Deck<>();
		ControllerHub hub = new ControllerHub();
		Player[] players = new Player[4];
		TestController[] con = new TestController[players.length];
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player("Player " + i);
			con[i] = new TestController(players[i], hub);
			hub.setControllerForPlayer(players[i], con[i]);
		}
		GameState game = new GameState(players, adventureDeck, storyDeck);
		game.setController(hub);
		hub.SetGameState(game);
		
		storyDeck.AddCard(new QuestCard(QuestCard.QuestTypes.BoarHunt));
		storyDeck.AddCard(new ProsperityEvent());
		storyDeck.AddCard(new ChilvalrousDeed());
		for (int i = 0; i < 20; i++)
			adventureDeck.AddCard(new WeaponCard(WeaponTypes.BattleAx));
		
		players[0].AddCardToHand(new WeaponCard(WeaponTypes.Dagger));
		players[0].AddCardToHand(new WeaponCard(WeaponTypes.Excalibur));
		players[0].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
		players[1].AddCardToHand(new WeaponCard(WeaponTypes.BattleAx));
		players[2].AddCardToHand(new WeaponCard(WeaponTypes.Horse));
		players[2].AddCardToHand(new FoeCard(FoeTypes.Boar));
		players[2].AddCardToHand(new FoeCard(FoeTypes.EvilKnight));
		
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[2].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[2].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[3];
				toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
				toDo[0].SetTarget(state, state.getCurrentQuest().getStage(0));
				toDo[1] = player.getPlayersCards()[0].GetAbilities()[0];
				toDo[1].SetTarget(state, state.getCurrentQuest().getStage(1));
				toDo[2] = player.getPlayersCards()[2].GetAbilities()[0];
				toDo[2].SetTarget(state, state.getCurrentQuest().getStage(1));
				hub.UseCardAbilities(player, toDo);
				/*for (int i = 0; i < state.getCurrentQuest().GetNumberOfStages(); i++) {
					System.out.println("Stage " + i);
					System.out.println("\tTotal BP: " + state.getCurrentQuest().GetBattlePointsForStage(i, game));
					System.out.print("\t");
					for (AdventureCard card : state.getCurrentQuest().GetCardsForStage(i))
						System.out.print(card.getName() + ", ");
					System.out.println();
				}*/
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[3].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[1];
				toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
				hub.UseCardAbilities(player, toDo);
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[1];
				toDo[0] = player.getPlayersCards()[0].GetAbilities()[0];
				hub.UseCardAbilities(player, toDo);
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		
		game.startGame();
		
		/*for(Player p : game.getPlayers()) {
			System.out.println(p);
		}*/
		
		assertEquals(true, players[0].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(2, players[0].GetRank().getCurrentShields());
		assertEquals(6, players[0].getPlayersCards().length);
		
		assertEquals(true, players[1].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(3, players[1].GetRank().getCurrentShields());
		assertEquals(3, players[1].getPlayersCards().length);
		
		assertEquals(true, players[2].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(3, players[2].GetRank().getCurrentShields());
		assertEquals(7, players[2].getPlayersCards().length);
		
		assertEquals(true, players[3].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(3, players[3].GetRank().getCurrentShields());
		assertEquals(2, players[3].getPlayersCards().length);
	}
	
	public void testStartQuest() {
		Deck<StoryCard> storyDeck = new Deck<>();
		Deck<AdventureCard> adventureDeck = new Deck<>();
		ControllerHub hub = new ControllerHub();
		Player[] players = new Player[4];
		TestController[] con = new TestController[players.length];
		for(int i = 0; i < players.length; i++) {
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
		
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[2].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[2].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[3];
				toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
				toDo[0].SetTarget(state, state.getCurrentQuest().getStage(0));
				toDo[1] = player.getPlayersCards()[0].GetAbilities()[0];
				toDo[1].SetTarget(state, state.getCurrentQuest().getStage(1));
				toDo[2] = player.getPlayersCards()[2].GetAbilities()[0];
				toDo[2].SetTarget(state, state.getCurrentQuest().getStage(1));
				hub.UseCardAbilities(player, toDo);
				/*for (int i = 0; i < state.getCurrentQuest().GetNumberOfStages(); i++) {
					System.out.println("Stage " + i);
					System.out.println("\tTotal BP: " + state.getCurrentQuest().GetBattlePointsForStage(i, game));
					System.out.print("\t");
					for (AdventureCard card : state.getCurrentQuest().GetCardsForStage(i))
						System.out.print(card.getName() + ", ");
					System.out.println();
				}*/
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Yes; }
		});
		con[3].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.No; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[1];
				toDo[0] = player.getPlayersCards()[1].GetAbilities()[0];
				hub.UseCardAbilities(player, toDo);
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {
				Ability[] toDo = new Ability[1];
				toDo[0] = player.getPlayersCards()[0].GetAbilities()[0];
				hub.UseCardAbilities(player, toDo);
			}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[0].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		con[1].addCommand(new TestCommand() {
			@Override
			public void doCommand(Player player, ControllerHub hub, GameState state) {}
			@Override
			public ControllerResponse getResponse(Player player, ControllerHub hub, GameState state) { return ControllerResponse.Continue; }
		});
		
		game.startQuest(new QuestCard(QuestCard.QuestTypes.BoarHunt));
		
		/*for(Player p : game.getPlayers()) {
			System.out.println(p);
		}*/
		
		assertEquals(true, players[0].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(2, players[0].GetRank().getCurrentShields());
		assertEquals(4, players[0].getPlayersCards().length);
		
		assertEquals(true, players[1].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(0, players[1].GetRank().getCurrentShields());
		assertEquals(1, players[1].getPlayersCards().length);
		
		assertEquals(true, players[2].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(0, players[2].GetRank().getCurrentShields());
		assertEquals(5, players[2].getPlayersCards().length);
		
		assertEquals(true, players[3].GetRank().getCurrentRank() == Ranks.Squire);
		assertEquals(0, players[3].GetRank().getCurrentShields());
		assertEquals(0, players[3].getPlayersCards().length);
	}
	
}

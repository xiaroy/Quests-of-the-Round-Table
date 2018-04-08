package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AIController;
import controller.Controller;
import controller.ControllerHub;
import controller.UIController;
import controller.AI.*;
import model.game.GameState;
import model.player.Player;
import server.SpringServer;
import ui.gameboard.GameBoardPanel;
import ui.mainmenu.MainMenuPanel;
import ui.mainmenu.Scenario;
import view.GameView;

public class MainFrame extends JFrame {

	public static void main(String[] args) {
		MainFrame game = new MainFrame();
	}
	
	private ControllerHub cHub;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Controller> controllers = new ArrayList<>();
	private GameState game;
	
	public MainFrame() {
		super("Quest for the Round Table");
		
		this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel(new MainMenuPanel(this));
		this.setVisible(true);
	}
	
	public ControllerHub getControllerHub() { return cHub; }
	public GameState getGameState() { return game; }
	
	public void beginWebGame(int players, int AI) {
		if (AI < players && players <= 4 && players >= 2) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					cHub = new ControllerHub();
					
					SpringServer.setMainFrame(MainFrame.this);
					SpringServer.startServer();
					
					while (MainFrame.this.players.size() < players) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					for (int i = 0; i < AI; i++) {
						Player p = new Player("AI " + i);
						AIController c = new AIController(p, cHub, new AISponsorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
						addPlayer(p, c);
					}
					
					Player[] players = (Player[])MainFrame.this.players.toArray(new Player[MainFrame.this.players.size()]);
					game = new GameState(players);

					game.setController(cHub);
					cHub.SetGameState(game);
					
					for (Controller c : controllers) {
						cHub.setControllerForPlayer(c.getPlayer(), c);
						c.updateDisplay();
					}
					
					for (Player p : players) {
						System.out.println(p.GetName());
					}
					
					//game.startGame();
				}
			}).start();
		}
	}
	
	public void addPlayer(Player p, Controller c) {
		players.add(p);
		controllers.add(c);
	}
	
	public void removePlayer(Controller c) {
		players.remove(c.getPlayer());
		controllers.remove(c);
	}
	
	public void beginGame(int players, int AI) {
		if (AI < players && players <= 4 && players >= 2) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					GameBoardPanel view = new GameBoardPanel(players - 1);
					game = new GameState(players);
					cHub = new ControllerHub();
					
					cHub.SetGameState(game);
					cHub.setBoardPanel(view);
					game.setController(cHub);
					view.setControllerHub(cHub);
					int i = 0;
					for (Player p : game.getPlayers()) {
						Controller c;
						if (i < players - AI) {
							c = new UIController(p, cHub, game, view);
							p.SetName("Player " + i);
						}
						else {
							c = new AIController(p, cHub, new AISponsorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
							p.SetName("AI " + (i - (players - AI)));
						}
						cHub.setControllerForPlayer(p, c);
						i++;
					}
					
					view.displayGame(new GameView(game, game.getPlayers()[0]));
					view.hideHand();
					setPanel(view);
					view.repaint();
					view.revalidate();
					game.startGame();
					
					setPanel(new MainMenuPanel(MainFrame.this));
				}
			}).start();
			
		}
	}
	
	public void beginGame(Scenario scenario) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				GameBoardPanel view = new GameBoardPanel(scenario.getGameState().getPlayers().length - 1);
				scenario.getControllerHub().setBoardPanel(view);
				view.setControllerHub(scenario.getControllerHub());
				
				view.displayGame(new GameView(scenario.getGameState(), scenario.getGameState().getPlayers()[0]));
				view.hideHand();
				setPanel(view);
				view.repaint();
				view.revalidate();
				scenario.getGameState().startGame();
				
				setPanel(new MainMenuPanel(MainFrame.this));
			}
		}).start();
	}
	
	private void setPanel(JPanel pnl) {
		this.setContentPane(pnl);
		this.pack();
		this.repaint();
		this.revalidate();
	}
}

package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AIController;
import controller.Controller;
import controller.ControllerHub;
import controller.UIController;
import controller.AI.*;
import model.game.GameState;
import model.player.Player;
import ui.gameboard.GameBoardPanel;
import ui.mainmenu.MainMenuPanel;
import ui.mainmenu.Scenario;
import view.GameView;

public class MainFrame extends JFrame {

	public static void main(String[] args) {
		MainFrame game = new MainFrame();
	}
	
	public MainFrame() {
		super("Quest for the Round Table");
		
		this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel(new MainMenuPanel(this));
		this.setVisible(true);
	}
	
	public void beginGame(int players, int AI) {
		if (AI < players && players <= 4 && players >= 2) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					GameBoardPanel view = new GameBoardPanel(players - 1);
					GameState state = new GameState(players);
					ControllerHub controller = new ControllerHub();
					
					controller.SetGameState(state);
					controller.setBoardPanel(view);
					state.setController(controller);
					view.setControllerHub(controller);
					int i = 0;
					for (Player p : state.getPlayers()) {
						Controller c;
						if (i < players - AI) {
							c = new UIController(p, controller, state, view);
							p.SetName("Player " + i);
						}
						else {
							c = new AIController(p, controller, new AISponsorStrategy2(), new AIQuestStrategy2(), new AITournamentStrategy2(), new AIBidStrategy2());
							p.SetName("AI " + (i - (players - AI)));
						}
						controller.setControllerForPlayer(p, c);
						i++;
					}
					
					view.displayGame(new GameView(state, state.getPlayers()[0]));
					view.hideHand();
					setPanel(view);
					view.repaint();
					view.revalidate();
					state.startGame();
					
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

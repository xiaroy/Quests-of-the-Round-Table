package ui.mainmenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.MainFrame;

public class MainMenuPanel extends JPanel {

	MainFrame main;
	
	JPanel pnlMenu, pnlGame, pnlScenario;
	JLabel lblTitle;
	
	public MainMenuPanel(MainFrame frame) {
		main = frame;
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		//this.setPreferredSize(new Dimension(800, 600));
		
		initMainMenuPnl();
		initstartGamePnl();
		initScenarioPnl();
		
		lblTitle = new JLabel("Quest for the Round Table");
		
		setPanel(pnlMenu);
	}
	
	private void initMainMenuPnl() {
		pnlMenu = new JPanel();
		pnlMenu.setLayout(new GridBagLayout());
		
		int row = 0;
		GridBagConstraints c = new GridBagConstraints();
		
		JButton btnGame = new JButton("Start Game");
		btnGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPanel(pnlGame);
			}
		});
		c.gridy = row;
		pnlMenu.add(btnGame, c);
		row++;
		
		JButton btnScenario = new JButton("Test Scenarios");
		btnScenario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPanel(pnlScenario);
			}
		});
		c.gridy = row;
		pnlMenu.add(btnScenario, c);
		row++;
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		c.gridy = row;
		pnlMenu.add(btnExit, c);
		row++;
	}
	
	private void initstartGamePnl() {
		pnlGame = new JPanel();
		pnlGame.setLayout(new GridBagLayout());
		
		int row = 0;
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel lblNumOfPlayers = new JLabel("How Many Players (2 - 4): ");
		JTextField txtNumOfPlayers = new JTextField("4");
		c.gridy = row;
		c.gridx = 0;
		pnlGame.add(lblNumOfPlayers, c);
		c.gridx = 1;
		pnlGame.add(txtNumOfPlayers, c);
		row++;
		
		JLabel lblNumOfAI = new JLabel("How Many AI (0 - 3): ");
		JTextField txtNumOfAI = new JTextField("0");
		c.gridy = row;
		c.gridx = 0;
		pnlGame.add(lblNumOfAI, c);
		c.gridx = 1;
		pnlGame.add(txtNumOfAI, c);
		row++;
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int players = Integer.parseInt(txtNumOfPlayers.getText());
				int ai = Integer.parseInt(txtNumOfAI.getText());
				main.beginGame(players, ai);
			}
		});
		c.gridx = 0;
		c.gridy = row;
		c.gridwidth = 2;
		pnlGame.add(btnStart, c);
		row++;
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPanel(pnlMenu);
			}
		});
		c.gridy = row;
		pnlGame.add(btnBack, c);
		row++;
	}
	
	private void initScenarioPnl() {
		pnlScenario = new JPanel();
		pnlScenario.setLayout(new GridBagLayout());
		
		int row = 0;
		GridBagConstraints c = new GridBagConstraints();
		
		JButton btnStart = new JButton("Start Scenario 1");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				main.beginGame(new Scenario());
			}
		});
		c.gridy = row;
		pnlScenario.add(btnStart, c);
		row++;
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPanel(pnlMenu);
			}
		});
		c.gridy = row;
		pnlScenario.add(btnBack, c);
		row++;
	}
	
	private void setPanel(JPanel pnl) {
		this.removeAll();
		this.add(lblTitle, BorderLayout.NORTH);
		this.add(pnl, BorderLayout.CENTER);

		main.pack();
		this.repaint();
		this.revalidate();
	}
}

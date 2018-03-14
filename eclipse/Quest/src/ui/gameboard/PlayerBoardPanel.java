package ui.gameboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.cards.Card;
import model.player.Rank;

public class PlayerBoardPanel extends JPanel {

	JTextArea txtName, txtCardsInHand, txtRank, txtShields;
	CardPanel pnlBoard;
	
	public PlayerBoardPanel() {
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(pnlInfo, BorderLayout.NORTH);
		
		pnlInfo.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1; c.weighty = 1;
		
		txtName = new JTextArea("Name: ");
		txtName.setEditable(false);
		c.gridx = 0; c.gridy = 0;
		pnlInfo.add(txtName, c);
		txtCardsInHand = new JTextArea("Cards: ");
		txtCardsInHand.setEditable(false);
		c.gridx = 1; c.gridy = 0;
		pnlInfo.add(txtCardsInHand, c);
		txtRank = new JTextArea("Rank: ");
		txtRank.setEditable(false);
		c.gridx = 2; c.gridy = 0;
		pnlInfo.add(txtRank, c);
		txtShields = new JTextArea("Shields: ");
		txtShields.setEditable(false);
		c.gridx = 3; c.gridy = 0;
		pnlInfo.add(txtShields, c);
		
		JScrollPane pnlscrlBoard = new JScrollPane();
		pnlBoard = new CardPanel();
		pnlscrlBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlscrlBoard.getViewport().add(pnlBoard);
		pnlscrlBoard.getHorizontalScrollBar().setUnitIncrement(8);
		this.add(pnlscrlBoard, BorderLayout.CENTER);
	}
	
	public void setupPlayerBoard(String name, Rank rank, CardLabel[] cards, int cardsInHand) {
		txtName.setText("Name: " + name);
		txtCardsInHand.setText("Cards: " + cardsInHand);
		txtRank.setText("Rank: " + rank.getCurrentRank());
		txtShields.setText("Shields: " + rank.getCurrentShields());
		pnlBoard.setCards(cards);
	}
}

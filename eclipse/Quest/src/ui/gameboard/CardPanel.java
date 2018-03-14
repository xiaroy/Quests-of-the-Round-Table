package ui.gameboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.cards.Card;

public class CardPanel extends JScrollPane {

	private JPanel pnlBoard;
	private CardLabel[] lblCards = new CardLabel[0];
	
	public CardPanel() {
		init();
	}
	
	private void init() {
		pnlBoard = new JPanel();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getViewport().add(pnlBoard);
		this.getHorizontalScrollBar().setUnitIncrement(8);
		
		/*this.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {}
			@Override
			public void componentMoved(ComponentEvent arg0) {}

			@Override
			public void componentResized(ComponentEvent arg0) {
				resizeCards();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {}
		});*/
	}
	
	public void setCards(CardLabel[] cards) {
		for (CardLabel lblCard : lblCards)
			pnlBoard.remove(lblCard);
		
		lblCards = cards;
		for (CardLabel lblCard : lblCards)
			pnlBoard.add(lblCard);
		
		pnlBoard.repaint();
		pnlBoard.revalidate();
	}
	
	public void hideCards() {
		for (CardLabel lblCard : lblCards)
			pnlBoard.remove(lblCard);
		
		lblCards = new CardLabel[lblCards.length];
		for (int i = 0; i < lblCards.length; i++) {
			lblCards[i] = new CardLabel(null);
			pnlBoard.add(lblCards[i]);
		}
		
		this.repaint();
		this.revalidate();
	}
	
	private void resizeCards() {
		for (int i = 0; i < lblCards.length; i++)
			lblCards[i].resize(this.getHeight() - this.getHorizontalScrollBar().getHeight() - 12);
	}
}

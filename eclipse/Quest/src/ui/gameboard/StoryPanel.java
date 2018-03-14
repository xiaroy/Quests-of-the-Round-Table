package ui.gameboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.cards.abilities.Ability;

public class StoryPanel extends JPanel {

	private CardLabel storyCard = null;
	private JPanel questPanel = null;
	
	private CardLabel[][] stageCards = null;
	private int currentDisplayStage = 0;
	
	private JButton btnNext, btnPrev;
	private JTextArea txtStage;
	private JPanel pnlStageControl, pnlStageCards;
	
	private GameBoardPanel board;
	
	public StoryPanel(GameBoardPanel board) {
		this.board = board;
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		questPanel = new JPanel();
		this.add(questPanel, BorderLayout.CENTER);
		
		JPanel pnlStages = new JPanel();
		pnlStages.setLayout(new  BorderLayout());
		this.add(pnlStages, BorderLayout.CENTER);
		
		pnlStageControl = new JPanel();
		pnlStageControl.setLayout(new  BorderLayout());
		pnlStages.add(pnlStageControl, BorderLayout.NORTH);
		
		btnPrev = new JButton("Prev");
		pnlStageControl.add(btnPrev, BorderLayout.WEST);
		btnNext = new JButton("Next");
		pnlStageControl.add(btnNext, BorderLayout.EAST);
		txtStage = new JTextArea("Stage: ");
		pnlStageControl.add(txtStage, BorderLayout.CENTER);
		
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDisplayStage--;
				displayStage();
			}
		});
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDisplayStage++;
				displayStage();
			}
		});
		
		
		pnlStageCards = new JPanel();
		JScrollPane sclStage = new JScrollPane(pnlStageCards);
		pnlStages.add(sclStage, BorderLayout.CENTER);
		
		pnlStageCards.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						board.stageSelected(currentDisplayStage);
					}
				}).start();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		displayStage();
	}
	
	public void setStoryCard(CardLabel card) {
		if (storyCard != null)
			this.remove(storyCard);
		
		storyCard = card;
		
		if (storyCard != null)
			this.add(storyCard, BorderLayout.NORTH);
	}
	
	public void setQuestStages(CardLabel[][] cards) {
		stageCards = cards;
		displayStage();
		
	}
	
	public void hideStage() {
		pnlStageCards.removeAll();
		for (CardLabel c : stageCards[currentDisplayStage]) {
			pnlStageCards.add(new CardLabel(null));
		}
		
		pnlStageCards.repaint();
		pnlStageCards.revalidate();
	}
	
	private void displayStage() {
		if (stageCards == null) {
			btnPrev.setEnabled(false);
			btnNext.setEnabled(false);
			txtStage.setText("");
			pnlStageCards.removeAll();
			return;
		}
		
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		pnlStageCards.removeAll();
		
		if (currentDisplayStage <= 0) {
			currentDisplayStage = 0;
			btnPrev.setEnabled(false);
		}
		else if (currentDisplayStage >= stageCards.length - 1) {
			currentDisplayStage = stageCards.length - 1;
			btnNext.setEnabled(false);
		}
		
		txtStage.setText("Stage: " + (currentDisplayStage + 1));
		for (CardLabel c : stageCards[currentDisplayStage]) {
			pnlStageCards.add(c);
		}
		
		pnlStageCards.repaint();
		pnlStageCards.revalidate();
	}
	
}

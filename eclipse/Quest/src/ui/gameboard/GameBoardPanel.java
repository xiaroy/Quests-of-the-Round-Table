package ui.gameboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ControllerHub;
import controller.ControllerHub.ControllerMessageType;
import controller.ControllerHub.ControllerResponse;
import controller.UIController;
import model.cards.AdventureCard;
import model.cards.Card;
import model.cards.abilities.Ability;
import model.game.QuestInfo;
import view.GameView;

public class GameBoardPanel extends JPanel {

	private PlayerBoardPanel[] oppBoard;
	
	private PlayerBoardPanel playerBoard;
	private CardPanel playerHand;
	private ControlPanel controls;
	
	private StoryPanel storyPanel;
	
	private ControllerHub hub = null;
	
	public GameBoardPanel(int numOfOpponents) {
		oppBoard = new PlayerBoardPanel[numOfOpponents];
		init();
	}
	
	private void init() {
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		int gridWidth = oppBoard.length;
		int cellWidth = 800 /(oppBoard.length + 1), cellHeight = 600 / 3;
		
		for (int i = 0; i < oppBoard.length; i++) {
			oppBoard[i] = new PlayerBoardPanel();
			c.gridx = i; c.gridy = 0;
			c.weightx = 1; c.weighty = 1;
			oppBoard[i].setOpaque(true);
			oppBoard[i].setBackground(new Color(100 * i, 0, 0));
			this.add(oppBoard[i], c);
		}
		
		playerBoard = new PlayerBoardPanel();
		c.gridx = 0; c.gridy = 1;
		c.gridwidth = gridWidth;
		c.weightx = 1; c.weighty = 1;
		//playerBoard.setPreferredSize(new Dimension(cellWidth * gridWidth, cellHeight));
		playerBoard.setBackground(Color.GREEN);
		this.add(playerBoard, c);
		
		playerHand = new CardPanel();
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = gridWidth;
		c.weightx = 1; c.weighty = 1;
		//playerHand.setPreferredSize(new Dimension(cellWidth * gridWidth, cellHeight));
		playerBoard.setBackground(Color.BLUE);
		this.add(playerHand, c);
		
		controls = new ControlPanel();
		c.gridx = gridWidth + 1; c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0; c.weighty = 1;
		//controls.setPreferredSize(new Dimension(cellWidth, cellHeight));
		playerBoard.setBackground(Color.GRAY);
		this.add(controls, c);
		
		storyPanel = new StoryPanel(this);
		c.gridx = gridWidth + 1; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = 2;
		c.weightx = 0; c.weighty = 1;
		//storyPanel.setPreferredSize(new Dimension(cellWidth, cellHeight * 2));
		playerBoard.setBackground(Color.BLACK);
		this.add(storyPanel, c);
	}
	
	public void setControllerHub(ControllerHub hub) { this.hub = hub; }
	
	public void displayGame(GameView view) {
		for (int i = 0; i < oppBoard.length; i++) {
			CardLabel[] cardsInPlay = new CardLabel[view.GetOtherPlayerBoard(i).length];repaint();
			for (int i2 = 0; i2 < cardsInPlay.length; i2++)
				cardsInPlay[i2] = new CardLabel(view.GetOtherPlayerBoard(i)[i2]);
			oppBoard[i].setupPlayerBoard(view.GetOtherPlayerName(i), view.GetOtherPlayerRank(i), cardsInPlay, view.GetOtherPlayerHandCount(i));
		}
		
		CardLabel[] cards = new CardLabel[view.GetPerspectiveBoard().length];
		for (int i = 0; i < cards.length; i++)
			cards[i] = new CardLabel(view.GetPerspectiveBoard()[i]);
		playerBoard.setupPlayerBoard(view.GetPerspectiveName(), view.GetPerspectiveRank(), cards, view.GetPerspectiveHand().length);
		
		cards = new CardLabel[view.GetPerspectiveHand().length];
		for (int i = 0; i < cards.length; i++)
			cards[i] = new CardLabel(view.GetPerspectiveHand()[i], this);
		playerHand.setCards(cards);
		
		storyPanel.setStoryCard(new CardLabel(view.GetCurrentStory()));
		
		if (view.getCurrentQuest() != null) {
			QuestInfo info = view.getCurrentQuest();
			CardLabel[][] stages = new CardLabel[info.GetNumberOfStages()][];
			for (int i = 0; i < info.GetNumberOfStages(); i++) {
				stages[i] = new CardLabel[info.GetCardsForStage(i).length];
				for (int j = 0 ; j < info.GetCardsForStage(i).length; j++) {
					if (i < info.getCurStage() || info.getCurStage() == -1)
						stages[i][j] = new CardLabel(info.GetCardsForStage(i)[j]);
					else stages[i][j] = new CardLabel(null);
				}
			}
			storyPanel.setQuestStages(stages);
		}
		else storyPanel.setQuestStages(null);
		
		this.repaint();
		this.revalidate();
	}
	
	public void updateView() {
		if (lastController != null)
			displayGame(lastController.getPlayerView());
		else if (hub != null)
			displayGame(hub.GetPerspective());
	}
	
	public void hideHand() {
		playerHand.hideCards();
	}
	
	public void hideStages() {
		storyPanel.hideStage();
	}
	
	
	public Ability[] getUsableAbilities(Card card) {
		if (!(card instanceof AdventureCard))
			return null;
		
		ArrayList<Ability> usable = new ArrayList<>();
		for (Ability abl : ((AdventureCard)card).GetAbilities())
			if (lastController.canUseAbility(abl))
				usable.add(abl);
		
		return usable.toArray(new Ability[usable.size()]);
	}
	
	private Ability requiresTarget = null;
	private boolean targetReceived = true;
	public void useAbility(Ability ability) {
		if (lastController.doesAbilityTarget(ability)) {
			targetReceived = false;
			requiresTarget = ability;
		}
		
		while(!targetReceived) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		requiresTarget = null;
		lastController.useAbility(ability);
		displayGame(lastController.getPlayerView());
	}
	
	public void stageSelected(int i) {
		if (requiresTarget == null && lastController.getPlayerView().getCurrentQuest() != null)
			return;
		 boolean canTarget = lastController.canAbilityTarget(requiresTarget, lastController.getPlayerView().getCurrentQuest().getStage(i));
		 if (canTarget) {
			 lastController.setAbilityTarget(requiresTarget, lastController.getPlayerView().getCurrentQuest().getStage(i));
			 targetReceived = true;
		 }
	}
	
	private UIController lastController = null;
	public ControllerResponse getInput(UIController controller, ControllerMessageType type) {
		GameView view = controller.getPlayerView();
		if (controller != lastController) {
			hideHand();
			JOptionPane.showMessageDialog(this, "Please pass control to " + view.GetPerspectiveName());
			lastController = controller;
		}
		displayGame(view);
		
		ControllerResponse response = ControllerResponse.Continue;
		
		switch (type){
		case WillSponsor:
			boolean input = controls.getInput("Do you wish to Sponor this quest", ControlPanel.ControlTypes.YesNo);
			if (input) response = ControllerResponse.Yes;
			else response = ControllerResponse.No;
			break;
		case CreateQuestStages:
			controls.getInput("Select the Cards to use in the Quest", ControlPanel.ControlTypes.Continue);
			hideStages();
			break;
		case ParticipateInQuest:
			input = controls.getInput("Do you wish to Participate this quest", ControlPanel.ControlTypes.YesNo);
			if (input) response = ControllerResponse.Yes;
			else response = ControllerResponse.No;
			break;
		case PlayForQuest:
			controls.getInput("Select the Cards to use in the Quest", ControlPanel.ControlTypes.Continue);
			break;
		case BidForTest:
			controls.getInput("How many Cards do you want to bid?", ControlPanel.ControlTypes.Continue);
			break;
		case ParticipateInTournament:
			input = controls.getInput("Do you wish to Participate this Tournament", ControlPanel.ControlTypes.YesNo);
			if (input) response = ControllerResponse.Yes;
			else response = ControllerResponse.No;
			break;
		case PlayForTournament:
			controls.getInput("Select the Cards to use in the Tournament", ControlPanel.ControlTypes.Continue);
			break;
		}
		
		return response;
	}
	
	public void sendGeneralMessage(GameView view, String msg) {
		if (view != null)
			displayGame(view);
		else updateView();
		hideHand();
		
		controls.getInput(msg, ControlPanel.ControlTypes.Continue);
	}
}

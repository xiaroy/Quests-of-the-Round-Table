package ui.gameboard;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.cards.Card;
import model.cards.abilities.Ability;

public class CardLabel extends JLabel {

	BufferedImage bufferImg = null;
	ImageIcon lblImg = null;
	
	Card card;
	GameBoardPanel board;
	
	public CardLabel(Card card) {
		this(card, null);
	}
	
	public CardLabel(Card card, GameBoardPanel board) {
		this.card = card;
		this.board = board;
		if (card != null)
			this.setToolTipText(card.getName());
		else this.setToolTipText("Unkown");
		
		try {
			if (card != null)
				bufferImg = ImageIO.read(new File("./res/" + card.GetCardType() + "/" + card.getName() + ".png"));
			else bufferImg = ImageIO.read(new File("./res/Back.png"));
		} catch (IOException e) {
			try {
				bufferImg = ImageIO.read(new File("./res/Back.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (card != null && board != null) {
			this.addMouseListener(new MouseListener() {

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
							Ability[] abilities = board.getUsableAbilities(card);
							if (abilities == null || abilities.length == 0)
								return;
							else if (abilities.length == 1)
								board.useAbility(abilities[0]);
						}
					}).start();
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
		}
		
		this.resize(150);
	}
	
	public void resize(int height) {
		if (bufferImg == null)
			return;
		
		Image dImg = bufferImg.getScaledInstance((int)(height * ((float)bufferImg.getWidth() / (float)bufferImg.getHeight())), height, Image.SCALE_SMOOTH);
		lblImg = new ImageIcon(dImg);
		this.setIcon(lblImg);
		
	}
	
}

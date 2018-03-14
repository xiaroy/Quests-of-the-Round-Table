package ui.gameboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ControlPanel extends JPanel{

	private JTextArea txtMsg;
	private JPanel pnlInput;
	
	public ControlPanel() {
		init();
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		txtMsg = new JTextArea("Test");
		txtMsg.setEditable(false);
		txtMsg.setLineWrap(true);
		txtMsg.setWrapStyleWord(true);
		this.add(txtMsg, BorderLayout.CENTER);
		
		pnlInput = new JPanel();
		this.add(pnlInput, BorderLayout.SOUTH);
	}
	
	public boolean getInput(String msg, ControlTypes msgType) {
		txtMsg.setText(msg);
		
		class InputScope { public boolean input = false; }
		final InputScope inputRecieved = new InputScope(), input = new InputScope();
		
		switch (msgType) {
		case YesNo:
			JButton btnYes = new JButton("Yes");
			btnYes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					input.input = true;
					inputRecieved.input = true;
				}
			});
			pnlInput.add(btnYes);
			JButton btnNo = new JButton("No");
			btnNo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					input.input = false;
					inputRecieved.input = true;
				}
			});
			pnlInput.add(btnNo);
			break;
		case Continue:
			JButton btnCon = new JButton("Continue");
			btnCon.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					inputRecieved.input = true;
				}
			});
			pnlInput.add(btnCon);
			break;
		}
		
		this.repaint();
		this.revalidate();
		while (!inputRecieved.input) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pnlInput.removeAll();
		
		return input.input;
	}
	
	public enum ControlTypes{
		YesNo,
		Continue;
	}
	
}

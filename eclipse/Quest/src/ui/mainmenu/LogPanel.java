package ui.mainmenu;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogPanel extends JPanel {

	private JTextArea txtLogs = new JTextArea();
	
	public void addLog(String str) {
		txtLogs.append(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ": " + str + "\n");
	}
}

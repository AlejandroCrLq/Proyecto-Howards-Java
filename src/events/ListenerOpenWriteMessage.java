package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.WriteMessage;

public class ListenerOpenWriteMessage implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		new WriteMessage().setVisible(true);
	}
}
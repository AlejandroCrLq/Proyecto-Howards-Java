package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.WriteMessageWindow;

public class ListenerOpenWriteMessage implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		new WriteMessageWindow().setVisible(true);
	}
}
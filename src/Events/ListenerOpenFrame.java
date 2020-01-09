package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ListenerOpenFrame implements ActionListener {

	private JFrame destiny;
	private JFrame origin;

	public ListenerOpenFrame(JFrame origin, JFrame destiny) {
		this.origin = origin;
		this.destiny = destiny;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		origin.setVisible(false);
		destiny.setVisible(true);
	}
}

package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Message;
import javax.mail.MessagingException;

import interfaces.WriteMessage;

public class ListenerAnswerMessage implements ActionListener {
	private final Message message;

	public ListenerAnswerMessage(Message message) {
		this.message = message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		WriteMessage write = new WriteMessage();
		try {
			String from1 = message.getFrom()[0].toString();
			try {
				from1 = from1.substring(from1.indexOf("<") + 1, from1.indexOf(">"));
			} catch (IndexOutOfBoundsException ex) {
				// Normal from, without substring itself
			}
			write.getTextFor().setText(from1);

			write.getTextSubject().setText("RE: " + message.getSubject());

			write.getTextFor().setEnabled(false);
			write.getTextSubject().setEnabled(false);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
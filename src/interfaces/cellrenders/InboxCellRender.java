package interfaces.cellrenders;

import java.awt.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * La clase InboxCellRender se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class InboxCellRender extends DefaultListCellRenderer {

	private DefaultListModel<Message> messageModel;

	public InboxCellRender(DefaultListModel<Message> messageModel) {
		this.messageModel = messageModel;
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		System.out.println("Hola");
		Message message = messageModel.getElementAt(index);
		try {
			setText(message.getFrom()[0].toString());
		} catch (MessagingException e) {
			System.out.println("Ha sucedido un error");
		}
		return this;
	}

}

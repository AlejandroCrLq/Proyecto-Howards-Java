package interfaces;

import java.awt.Component;
import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * La clase MessageRender se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 18/12/2019
 */

public class MessageRender extends JLabel implements ListCellRenderer<Message> {

	public MessageRender() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Message> list, Message value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String from;
		try {
			from = MimeUtility.decodeText(value.getFrom()[0].toString()).replaceAll("\"", "");
			if (from.contains("<")) {
				from = from.substring(0, from.indexOf("<"));
			}
			setText(from + ": " + value.getSubject());
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

		} catch (MessagingException e) {
			System.out.println("Ha sucedido un error");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Ha sucedido un error");
		}
		return this;
	}

}

package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

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
			setHorizontalAlignment(SwingConstants.LEFT);
			setVerticalAlignment(SwingConstants.CENTER);
			from = MimeUtility.decodeText(value.getFrom()[0].toString()).replaceAll("\"", "");
			if (from.contains("<")) {
				from = from.substring(0, from.indexOf("<"));
			}
			if (value.isSet(Flags.Flag.SEEN)) {
				setText("Visto - ");
			} else {
				setText("No visto - ");
			}
			setText(getText() + from + " - " + value.getSubject());
			if (value.getSentDate() != null) {
				setText(getText() + " : " + value.getSentDate().toGMTString());
			}
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
				setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
				setBorder(BorderFactory.createRaisedBevelBorder());
			}
			setMinimumSize(new Dimension(getWidth(), getHeight() + 30));
			setPreferredSize(new Dimension(getWidth(), getHeight() + 30));
			setMaximumSize(new Dimension(getWidth(), getHeight() + 30));
		} catch (MessagingException e) {
			System.out.println("Ha sucedido un error");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Ha sucedido un error");
		}
		return this;
	}

}

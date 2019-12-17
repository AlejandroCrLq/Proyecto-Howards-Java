package interfaces;

import java.awt.Component;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * La clase FilePartRender se encarga de 
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class FilePartRender extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if(value instanceof Part) {
			Part part = (Part) value;
			try {
				setText(part.getFileName());
				if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
				return this;
			} catch (MessagingException e) {
				System.err.println("A loading message error has happend");
			}
		}
			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
}

package interfaces;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La clase DoubleLabel se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 09/01/2020
 */

public class MessagePanel extends JPanel {
	private JLabel lblContext;
	private JLabel lblDate;

	/**
	 * Create the panel.
	 */
	public MessagePanel() {
		setLayout(new BorderLayout(0, 0));

		lblDate = new JLabel("Date");
		add(lblDate, BorderLayout.EAST);

		lblContext = new JLabel("Context");
		add(lblContext, BorderLayout.WEST);
	}

	public void setContextText(String context) {
		lblContext.setText(context);
	}

	public void setDateText(String date) {
		lblDate.setText(date);
	}

}

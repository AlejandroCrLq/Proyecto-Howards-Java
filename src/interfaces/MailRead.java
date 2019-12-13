package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JButton;

public class MailRead extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public MailRead(Message message) throws MessagingException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("De :");
		lblDe.setText(lblDe.getText() + message.getFrom()[0]);
		lblDe.setBounds(27, 26, 521, 14);
		contentPane.add(lblDe);
		
		JLabel lblAsunto = new JLabel("Asunto: ");
		lblAsunto.setText(lblAsunto.getText() + message.getSubject());
		lblAsunto.setBounds(37, 53, 521, 14);
		contentPane.add(lblAsunto);
		
		textField = new JTextField();
		textField.setBounds(47, 78, 503, 160);
		textField.setText(message.getContent().toString());
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(113, 266, 112, 23);
		contentPane.add(btnResponder);
	}
}

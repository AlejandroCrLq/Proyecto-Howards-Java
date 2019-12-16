/*
 * Date:12/12/2019
 * 
 * Author:Francisco Manuel Rodriguez Martin
 * 
 * Description: Class that shows the window where we can write and send mails
 * 
 * Version:1.0
 */
package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WriteMessage extends JFrame {
	private JPanel contentPane;
	private JTextField textFor;
	private JTextField textSubject;
	private JTextArea textMessage;
	private JButton btnSend;

	/**
	 * Create the frame and fill it.
	 */
	public WriteMessage() {
		setTitle("Universidad de Howards");
		setBounds(100, 100, 800, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JLabel lblFor = new JLabel("Para");
		lblFor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFor.setBounds(40, 45, 75, 25);
		layeredPane.add(lblFor);

		JLabel lblSubject = new JLabel("Asunto");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubject.setBounds(40, 85, 97, 19);
		layeredPane.add(lblSubject);

		JLabel lblMessage = new JLabel("Mensaje");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMessage.setBounds(40, 125, 97, 19);
		layeredPane.add(lblMessage);

		textFor = new JTextField();
		textFor.setBounds(140, 45, 600, 22);
		layeredPane.add(textFor);
		textFor.setColumns(10);

		textSubject = new JTextField();
		textSubject.setBounds(140, 85, 600, 22);
		layeredPane.add(textSubject);
		textSubject.setColumns(10);

		textMessage = new JTextArea(30, 80);
		textMessage.setBounds(140, 125, 600, 200);
		layeredPane.add(textMessage);
		textMessage.setColumns(10);
		textMessage.setLineWrap(true);
		textMessage.setWrapStyleWord(true);

		btnSend = new JButton("Enviar");
		btnSend.setBounds(360, 335, 97, 25);
		layeredPane.add(btnSend);
		setVisible(true);

	}

	/*
	 * Getter and setter methods
	 */
	public JTextField getTextFor() {
		return textFor;
	}

	public void setTextFor(JTextField textFor) {
		this.textFor = textFor;
	}

	public JTextField getTextSubject() {
		return textSubject;
	}

	public void setTextSubject(JTextField textSubject) {
		this.textSubject = textSubject;
	}

	public JTextArea getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(JTextArea textMessage) {
		this.textMessage = textMessage;
	}

	public JButton getBtnSend() {
		return btnSend;
	}
}

/*
 * Date:12/12/2019
 * 
 * Author:Francisco Manuel Rodriguez Martin
 * 
 * Description: Class that create the login window where the user can introduce his information to have access to the rest
 * of the aplication
 * 
 * Version:1.0
 */
package interfaces;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame {
	private JPanel contentPane;
	private JTextField eMailPasswordField;
	private JButton loginButton;
	private JTextField textPassword;
	private JTextField textUser;

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Universidad de Howards");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(56, 68, 75, 25);
		layeredPane.add(lblUser);

		JLabel lblPassword = new JLabel("Contrase\u00F1a Campus");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(53, 125, 97, 19);
		layeredPane.add(lblPassword);

		textUser = new JTextField();
		textUser.setBounds(228, 68, 116, 22);
		layeredPane.add(textUser);
		textUser.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setBounds(228, 122, 116, 22);
		layeredPane.add(textPassword);
		textPassword.setColumns(10);

		loginButton = new JButton("Iniciar");
		loginButton.addActionListener(arg0 -> {
		});
		loginButton.setBounds(144, 251, 97, 25);
		layeredPane.add(loginButton);

		JLabel lblEmailPass = new JLabel("Contrase\u00F1a eMail");
		lblEmailPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailPass.setBounds(53, 179, 150, 19);
		layeredPane.add(lblEmailPass);

		eMailPasswordField = new JPasswordField();
		eMailPasswordField.setColumns(10);
		eMailPasswordField.setBounds(228, 179, 116, 22);
		layeredPane.add(eMailPasswordField);
		setVisible(true);
	}

	public JButton getBtnNewButton() {
		return loginButton;
	}

	public JTextField geteMailPasswordField() {
		return eMailPasswordField;
	}

	public JTextField getPassword() {
		return textPassword;
	}

	public JTextField getUser() {
		return textUser;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		loginButton = btnNewButton;
	}

	public void setPassword(JTextField password) {
		textPassword = password;
	}

	public void setUser(JTextField user) {
		textUser = user;
	}

}

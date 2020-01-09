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
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class Login extends JFrame {
	private JPanel contentPane;
	private JButton loginButton;
	private JTextField textUser;
	private JTextField textPassword;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Universidad de Howards");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(null);
		
		Image icon = new ImageIcon(this.getClass().getResource("/imagenes/logo.jpg")).getImage();
		setIconImage(icon);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(71, 65, 75, 25);
		layeredPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(68, 122, 97, 19);
		layeredPane.add(lblPassword);
		
		textUser = new JTextField();
		textUser.setBounds(206, 68, 116, 22);
		layeredPane.add(textUser);
		textUser.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(206, 122, 116, 22);
		layeredPane.add(textPassword);
		textPassword.setColumns(10);
		
		loginButton = new JButton("Iniciar");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginButton.setBounds(142, 205, 97, 25);
		layeredPane.add(loginButton);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public JTextField getUser() {
		return textUser;
	}

	public void setUser(JTextField user) {
		this.textUser = user;
	}

	public JTextField getPassword() {
		return textPassword;
	}

	public void setPassword(JTextField password) {
		this.textPassword = password;
	}

	public JButton getBtnNewButton() {
		return loginButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.loginButton = btnNewButton;
	}

}

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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPassword;
	
	/**
	 * Create the frame and fill it.
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
		
		JButton btnStart = new JButton("Iniciar");

		btnStart.setBounds(142, 205, 97, 25);
		layeredPane.add(btnStart);
		this.setVisible(true);
	}

	/*
	 * Getter and setter methods
	 */
	public JTextField getTextUser() {
		return textUser;
	}

	public void setTextUser(JTextField textUser) {
		this.textUser = textUser;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(JTextField textPassword) {
		this.textPassword = textPassword;
	}
}

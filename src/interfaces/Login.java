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
	private JTextField user;
	private JTextField password;
	private JButton loginButton;

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
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(71, 65, 75, 25);
		layeredPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContrasea.setBounds(68, 122, 97, 19);
		layeredPane.add(lblContrasea);
		
		user = new JTextField();
		user.setBounds(206, 68, 116, 22);
		layeredPane.add(user);
		user.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(206, 122, 116, 22);
		layeredPane.add(password);
		password.setColumns(10);
		
		loginButton = new JButton("Iniciar");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginButton.setBounds(142, 205, 97, 25);
		layeredPane.add(loginButton);
		this.setVisible(true);
	}
	
	public JTextField getUser() {
		return user;
	}

	public void setUser(JTextField user) {
		this.user = user;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

	public JButton getBtnNewButton() {
		return loginButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.loginButton = btnNewButton;
	}

}

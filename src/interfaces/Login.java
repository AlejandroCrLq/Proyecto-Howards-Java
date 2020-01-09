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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	private JPanel contentPane;
	private JButton loginButton;
	private JTextField textPassword;
	private JTextField textUser;

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Universidad de Howards");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setLocationRelativeTo(null);

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
		loginButton.addActionListener(arg0 -> {
		});
		loginButton.setBounds(142, 205, 97, 25);
		layeredPane.add(loginButton);
		setVisible(true);
	}

	public JButton getBtnNewButton() {
		return loginButton;
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

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
	private JTextField textField;
	private JTextField textField_1;
	
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
		
		textField = new JTextField();
		textField.setBounds(206, 68, 116, 22);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(206, 122, 116, 22);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(142, 205, 97, 25);
		layeredPane.add(btnNewButton);
		this.setVisible(true);
	}
}

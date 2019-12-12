package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class WriteMessage extends JFrame {

	private JPanel contentPane;
	private JTextField textFor;
	private JTextField textSubject;
	private JTextField textMessage;

	/**
	 * Create the frame.
	 */
	public WriteMessage() {
		setTitle("*USUARIO*");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(375, 473, 97, 25);
		layeredPane.add(btnEnviar);
		
		JLabel lblNewLabel = new JLabel("Para");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(74, 36, 56, 16);
		layeredPane.add(lblNewLabel);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAsunto.setBounds(74, 65, 56, 16);
		layeredPane.add(lblAsunto);
		
		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMensaje.setBounds(74, 94, 72, 25);
		layeredPane.add(lblMensaje);
		
		textFor = new JTextField();
		textFor.setBounds(160, 35, 573, 22);
		layeredPane.add(textFor);
		textFor.setColumns(10);
		
		textSubject = new JTextField();
		textSubject.setBounds(160, 64, 573, 22);
		layeredPane.add(textSubject);
		textSubject.setColumns(10);
		
		textMessage = new JTextField();
		textMessage.setBounds(160, 97, 573, 363);
		layeredPane.add(textMessage);
		textMessage.setColumns(10);
		
		this.setVisible(true);
	}
}

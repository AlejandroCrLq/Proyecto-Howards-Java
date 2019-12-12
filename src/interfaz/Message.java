package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Message extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Message() {
		setTitle("*ASUNTO* de *USUARIO*");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblmessage = new JLabel("*MENSAJE*");
		lblmessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmessage.setBounds(26, 13, 734, 294);
		layeredPane.add(lblmessage);
		
		JLabel lblFiles = new JLabel("*ARCHIVOS*");
		lblFiles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFiles.setBounds(24, 320, 719, 110);
		layeredPane.add(lblFiles);
		this.setVisible(true);
	}
}

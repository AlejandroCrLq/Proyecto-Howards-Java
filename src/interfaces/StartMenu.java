package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {
		setTitle("Universidad de Howards");
		setBounds(100, 100, 450, 300);
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JButton btnMail = new JButton("Correo");
		btnMail.setBounds(50, 50, 150, 150);
		layeredPane.add(btnMail);
		JButton btnFTP = new JButton("FTP");		
		btnFTP.setBounds(235, 50, 150, 150);
		layeredPane.add(btnFTP);
		btnMail.addActionListener(null);
		btnFTP.addActionListener(null);
		this.add(layeredPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}

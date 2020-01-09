/*
 * Date:12/12/2019
 * 
 * Author:Francisco Manuel Rodriguez Martin
 * 
 * Description: Class that show up the star menu where we can decide to enter to either the FTP menu and the Mail screen
 * 
 * Version:1.0
 */
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
	private JButton btnFTP;
	private JButton btnMail;
	
	/**
	 * Create the frame and fill it.
	 */
	public StartMenu() {
		setTitle("Universidad de Howards");
		setBounds(100, 100, 450, 300);
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		btnMail = new JButton("Correo");
		btnMail.setBounds(50, 50, 150, 150);
		layeredPane.add(btnMail);
		btnFTP = new JButton("FTP");		
		btnFTP.setBounds(235, 50, 150, 150);
		layeredPane.add(btnFTP);
		this.add(layeredPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/*
	 * Getter methods
	 */
	public JButton getBtnFTP() {
		return btnFTP;
	}

	public JButton getBtnMail() {
		return btnMail;
	}
}

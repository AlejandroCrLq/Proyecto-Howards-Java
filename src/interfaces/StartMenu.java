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
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		setBounds(100, 100, 900, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		btnMail = new JButton("");
		btnMail.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/carterochiquito2.png")));
		btnMail.setBounds(65, 70, 389, 593);
		layeredPane.add(btnMail);
		
		Image icon = new ImageIcon(this.getClass().getResource("/imagenes/logo.jpg")).getImage();
		setIconImage(icon);
		
		btnFTP = new JButton("");
		btnFTP.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/FTP.png")));
		btnFTP.setBounds(477, 70, 367, 593);
		layeredPane.add(btnFTP);
		
		JLabel lblNewLabel = new JLabel("Correos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(199, 41, 72, 22);
		layeredPane.add(lblNewLabel);
		
		JLabel lblCargarArchivos = new JLabel("Cargar AR'chivos");
		lblCargarArchivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCargarArchivos.setBounds(580, 41, 175, 16);
		layeredPane.add(lblCargarArchivos);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
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

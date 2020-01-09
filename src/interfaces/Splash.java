/*
 * Date: 12/12/2019
 * 
 * Author: Francisco Manuel Rodriguez Martin
 * 
 * Description: Class that make a splash screen at the start
 * 
 * Version:1.0
 */
package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.ConnectionToDatabase;
import general.LoginController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Splash extends JFrame implements Runnable {

	Thread splash = new Thread();
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Splash() {
		// frame = new Splash();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setUndecorated(true);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Splash.class.getResource("/interfaces/logo.jpg")));
		contentPane.add(label, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		try {
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			Thread.sleep(4000);
			Login log = new Login();
			ConnectionToDatabase connect = new ConnectionToDatabase();
			LoginController control = new LoginController(log, connect);
			control.addListeners();
			this.dispose();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

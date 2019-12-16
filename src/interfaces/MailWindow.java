package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import javax.swing.JList;

public class MailWindow extends JFrame {
	MailWindow frame = new MailWindow();
	private JPanel contentPane;
	private JButton btnAbout;
	private JButton btnOpenFTP;
	private JButton btnWriteMail;
	private JList listUserMails;

	/**
	 * Create the frame.
	 */
	public MailWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		btnOpenFTP = new JButton("FTP");
		menuBar.add(btnOpenFTP);
		
		btnWriteMail = new JButton("Redactar");
		menuBar.add(btnWriteMail);
		
		btnAbout = new JButton("Acerca de");
		menuBar.add(btnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		listUserMails = new JList();
		contentPane.add(listUserMails, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	/*
	 * Getter and setter methods
	 */
	public JButton getBtnAbout() {
		return btnAbout;
	}
	public JButton getBtnOpenFTP() {
		return btnOpenFTP;
	}
	public JButton getBtnWriteMail() {
		return btnWriteMail;
	}
	public JList getListUserMails() {
		return listUserMails;
	}
}

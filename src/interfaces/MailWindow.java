/*
 * Date:12/12/2019
 * 
 * Author:Jorge Rico Vivas
 * 
 * Description:Windows that allows to see the received emails and let us access to the write message and ftp windows
 * 
 * Version:1.0
 */
package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mail.MailInbox;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JList;

public class MailWindow extends JFrame {
	private JPanel contentPane;
	private JButton btnAbout;
	private JButton btnOpenFTP;
	private JButton btnWriteMail;
	private JList listUserMails;
	private MailInbox inbox;

	/**
	 * Create the frame.
	 * @throws MessagingException 
	 */
	public MailWindow() throws MessagingException {
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
		
		MailInbox mailTools = new MailInbox();
		try {
			mailTools.fillInbox(listUserMails);
			mailTools.addListener(listUserMails);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

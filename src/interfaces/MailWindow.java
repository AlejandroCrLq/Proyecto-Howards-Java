package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MailWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnAbout;
	private JButton btnOpenFTP;
	private JButton btnWriteMail;
	private JList listUserMails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailWindow frame = new MailWindow();
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
		Vector vectorDeEjemploHOLAJUANJO = new Vector();
		vectorDeEjemploHOLAJUANJO.add("Mail de Jorge: Hola Juanjo");
		vectorDeEjemploHOLAJUANJO.add("Mail de Jorge: Adios Juanjo");
		listUserMails.setListData(vectorDeEjemploHOLAJUANJO);
		contentPane.add(listUserMails, BorderLayout.CENTER);
	}

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

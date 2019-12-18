package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import mail.InboxCellRender;
import mail.MailInbox;

public class MailWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnAbout;
	private JButton btnOpenFTP;
	private JButton btnWriteMail;
	private JList<Message> listUserMails;
	private MailInbox inbox;

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
	 * 
	 * @throws MessagingException
	 */
	public MailWindow() throws MessagingException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("");
		menuBar.add(menu);

		btnOpenFTP = new JButton("FTP");
		menuBar.add(btnOpenFTP);

		btnWriteMail = new JButton("Redactar");
		btnWriteMail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteMessage write = new WriteMessage();
			}
		});
		menuBar.add(btnWriteMail);

		btnAbout = new JButton("Acerca de");
		menuBar.add(btnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		DefaultListModel<Message> messageModel = new DefaultListModel<Message>();
		listUserMails = new JList<Message>(messageModel);
		listUserMails.setCellRenderer(new MessageRender());
		JScrollPane scroll = new JScrollPane(listUserMails);
		contentPane.add(scroll, BorderLayout.CENTER);

		MailInbox mailTools = new MailInbox(messageModel);
		try {
			mailTools.fillInbox();
			mailTools.addListener(listUserMails);
			mailTools.refresh();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public JList<Message> getListUserMails() {
		return listUserMails;
	}
}

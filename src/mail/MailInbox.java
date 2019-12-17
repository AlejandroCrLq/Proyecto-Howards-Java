package mail;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import interfaces.MailRead;
import interfaces.MailWindow;

public class MailInbox {

	private static String mailHost = "pop.gmail.com";
	private static String mailUser = "acorralluque.sanjose@alumnado.fundacionloyola.net"; // COGERLOS DEL SITIO
	private static String mailPassword = "92405668"; //
	private static String mailPort = "995";
	private static Folder inbox;
	private DefaultListModel<Message> messageModel;
	private static Properties props;

	public MailInbox(JList<Message> mailInbox) throws MessagingException {
		props = new Properties();
		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.socketFactory.port", mailPort);
		props.put("mail.pop3.port", mailPort);
		props.put("mail.pop3.host", mailHost);
		props.put("mail.pop3.user", mailUser);
		props.put("mail.store.protocol", "pop3");

		Session session = Session.getDefaultInstance(props);
		Store store = session.getStore("pop3");
		store.connect(mailHost, mailUser, mailPassword);
		inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		messageModel = new DefaultListModel<Message>();
		mailInbox.setModel(messageModel);
		for (Message message : inbox.getMessages()) {
			messageModel.addElement(message);
		}
		mailInbox.setCellRenderer(new InboxCellRender());
	}
	// connect to my pop3 inbox

	/**
	 * Fills it with the messages from the connected user
	 * 
	 * @throws MessagingException
	 */
	public void fillInbox() throws MessagingException {

		Session session = Session.getDefaultInstance(props);
		Store store = session.getStore("pop3");
		store.connect(mailHost, mailUser, mailPassword);
		inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		messageModel.removeAllElements();
		for (Message message : inbox.getMessages()) {
			messageModel.addElement(message);
		}
	}

	/**
	 * This grab the JList and add an Double Click Action listener in order to read
	 * the clicked email.
	 * 
	 * @param mailInbox
	 * @throws MessagingException
	 */
	public void addListener(JList mailInbox) throws MessagingException {
		mailInbox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					try {
						MailRead mailRead = new MailRead(messageModel.elementAt(mailInbox.getSelectedIndex()));
						mailRead.setVisible(true);
					} catch (MessagingException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void refresh(MailWindow window) {
		refreshMailThread refresh = null;
		try {
			refresh = new refreshMailThread(window);
		} catch (MessagingException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refresh.start();
	}

}

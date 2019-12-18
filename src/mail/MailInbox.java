package mail;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import interfaces.MailRead;

public class MailInbox {

	private static String mailHost = "pop.gmail.com";
	private static String mailUser = "acorralluque.sanjose@alumnado.fundacionloyola.net"; // COGERLOS DEL SITIO
	private static String mailPassword = "92405668"; //
	private static String mailPort = "995";
	private static Folder inbox;
	private DefaultListModel<Message> messageModel;
	private static Properties props;
	private ArrayList<String> identifications = new ArrayList<String>();
	private Store store;

	public MailInbox(DefaultListModel<Message> messageModel) throws MessagingException {
		props = new Properties();
		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.socketFactory.port", mailPort);
		props.put("mail.pop3.port", mailPort);
		props.put("mail.pop3.host", mailHost);
		props.put("mail.pop3.user", mailUser);
		props.put("mail.store.protocol", "pop3");
		this.messageModel = messageModel;
		fillInbox();
	}
	// connect to my pop3 inbox

	/**
	 * Fills it with the messages from the connected user
	 * 
	 * @throws MessagingException
	 */
	public void fillInbox() throws MessagingException {
		Session session = Session.getDefaultInstance(props);
		store = session.getStore("pop3");
		store.connect(mailHost, mailUser, mailPassword);
		inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);

		for (Message message : inbox.getMessages()) {
			MimeMessage mime = (MimeMessage) message;
			// Mensaje ya existe
			if (!identifications.contains(mime.getMessageID())) {
				messageModel.insertElementAt(message, 0);
				identifications.add(mime.getMessageID());
			}
		}
	}

	/**
	 * This grab the JList and add an Double Click Action listener in order to read
	 * the clicked email.
	 * 
	 * @param mailInbox
	 * @throws MessagingException
	 */
	public void addListener(JList<Message> mailInbox) throws MessagingException {
		mailInbox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() >= 2) {
					readMessage(mailInbox);
				}
			}
		});
		mailInbox.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					readMessage(mailInbox);
				}
			}
		});
	}

	private void readMessage(JList<Message> mailInbox) {
		try {
			MailRead mailRead = new MailRead(messageModel.elementAt(mailInbox.getSelectedIndex()));
			mailRead.setVisible(true);
		} catch (MessagingException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Error abriendo el mensaje, puede que no haya conexión a internet o el mensaje haya sido eliminado");
		}
	}

	public void refresh() {
		try {
			refreshMailThread refresh = new refreshMailThread(this);
			refresh.start();
		} catch (MessagingException | InterruptedException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Error activando el refresco de ventanas, sus mensajes nuevos mensajes no se verán refrescados");
		}
	}

}

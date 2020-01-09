package mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Flags;
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
import interfaces.MailWindow;
import threads.ThreadRefreshMail;

public class MailInbox {

	/**
	 * acorralluque.sanjose@alumnado.fundacionloyola.net 92405668
	 */

	private static Folder inbox;
	private static String mailHost = "pop.gmail.com";
	private static String mailPassword = "92405668"; //
	private static String mailPort = "995";
	private static String mailUser = "acorralluque.sanjose@alumnado.fundacionloyola.net"; // COGERLOS DEL SITIO
	private static Properties props;
	private ArrayList<String> identifications = new ArrayList<String>();
	private JList<Message> mailInbox;
	private MailWindow mailWindow;
	private DefaultListModel<Message> messageModel;
	private Store store;

	public MailInbox(String mailUser, String mailPassword, DefaultListModel<Message> messageModel,
			MailWindow mailWindow) throws MessagingException {
		this.mailUser = mailUser;
		this.mailPassword = mailPassword;
		this.mailWindow = mailWindow;
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
	 * This grab the JList and add an Double Click Action listener in order to read
	 * the clicked email.
	 *
	 * @param mailInbox
	 * @throws MessagingException
	 */
	public void addListener(JList<Message> mailInbox) throws MessagingException {
		this.mailInbox = mailInbox;
		events.ListenerReadMessage listenerReadMail = new events.ListenerReadMessage(this);
		mailInbox.addMouseListener(listenerReadMail);
		mailInbox.addKeyListener(listenerReadMail);
	}

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
		inbox.open(Folder.READ_WRITE);
		mailWindow.setTitle(mailUser + " (" + inbox.getUnreadMessageCount() + ")");

		for (Message message : inbox.getMessages()) {
			MimeMessage mime = (MimeMessage) message;
			// Mensaje ya existe
			if (!identifications.contains(mime.getMessageID())) {
				messageModel.insertElementAt(message, 0);
				identifications.add(mime.getMessageID());
			}
		}
	}

	public void readMessage() {
		if (mailInbox.getSelectedIndex() != -1) {
			try {
				MailRead mailRead = new MailRead(messageModel.elementAt(mailInbox.getSelectedIndex()));
				mailRead.setVisible(true);
				inbox.setFlags(new Message[] { messageModel.elementAt(mailInbox.getSelectedIndex()) },
						new Flags(Flags.Flag.SEEN), true);

			} catch (MessagingException | IOException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error abriendo el mensaje, puede que no haya conexión a internet o el mensaje haya sido eliminado");
			}
		}
	}

	public void refresh() {
		try {
			ThreadRefreshMail refresh = new ThreadRefreshMail(this);
			refresh.start();
		} catch (MessagingException | InterruptedException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Error activando el refresco de ventanas, sus mensajes nuevos mensajes no se verán refrescados");
		}
	}

}

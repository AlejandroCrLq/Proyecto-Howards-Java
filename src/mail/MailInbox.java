package mail;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JList;

import interfaces.MailRead;
import interfaces.MailWindow;

public class MailInbox {

	private static String mailHost = "pop.gmail.com";
	private static String mailUser = "acorralluque.sanjose@alumnado.fundacionloyola.net";
	private static String mailPassword = "92405668";
	private static String mailPort = "995";
	private Message[] messages;

	
	public MailInbox() throws MessagingException {
		Properties props = new Properties();
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
		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);

		messages = inbox.getMessages();
	}
	// connect to my pop3 inbox

	/**
	 * This get the JList an then fills it with the messages from the connected user
	 * @param mailInbox
	 * @throws MessagingException
	 */
	public void fillInbox(JList mailInbox) throws MessagingException {

		Vector messageVector = new Vector();
		for(int i = messages.length-1;i>0;i--) {
			Vector messageMail = new Vector();
			messageMail.add(messages[i].getFrom()[0] + ": ");
			messageMail.add(messages[i].getSubject());
			messageVector.add(messageMail);
		}
		mailInbox.setListData(messageVector);

	}
	
	public void addListener(JList mailInbox) throws MessagingException {
		mailInbox.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            try {
						MailRead mailRead = new MailRead(messages[index]);
						mailRead.setVisible(true);
					} catch (MessagingException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        } else if (evt.getClickCount() == 3) {

		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
	}

}

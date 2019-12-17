package mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JList;

import interfaces.MailWindow;

public class refreshMailThread extends Thread {
	MailWindow window;
	static JList<Message> list;

	public refreshMailThread(MailWindow window) throws MessagingException, InterruptedException {
		this.window = window;
		list = window.getListUserMails();
	}

	public void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(10000);
				System.out.println("se refresca");
				list.removeAll();
				MailInbox inbox  = new MailInbox(list);
				inbox.fillInbox();
				window.repaint();
			} catch (InterruptedException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

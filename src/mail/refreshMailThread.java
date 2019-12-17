package mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JList;

import interfaces.MailWindow;

public class refreshMailThread extends Thread {

	private MailInbox inbox;

	public refreshMailThread(MailInbox inbox) throws MessagingException, InterruptedException {
		this.inbox = inbox;
	}

	public void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(10000);
				System.out.println("se refresca");
				inbox.fillInbox();
			} catch (InterruptedException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

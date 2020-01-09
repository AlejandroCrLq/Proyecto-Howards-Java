package mail;

import javax.mail.MessagingException;

import interfaces.MailWindow;

public class refreshMailThread extends Thread {

	private MailInbox inbox;
	private MailWindow mailWindow;

	public refreshMailThread(MailWindow mailWindow, MailInbox inbox) {
		this.mailWindow = mailWindow;
		this.inbox = inbox;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(10000);
				System.out.println("se refresca");
				inbox.fillInbox();
				mailWindow.repaint();
			} catch (InterruptedException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

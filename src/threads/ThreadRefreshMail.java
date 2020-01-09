package threads;

import javax.mail.MessagingException;

import mail.MailInbox;

public class ThreadRefreshMail extends Thread {

	private MailInbox inbox;

	public ThreadRefreshMail(MailInbox inbox) throws MessagingException, InterruptedException {
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
			} catch (InterruptedException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

package general;

import javax.mail.MessagingException;

import Events.ListenerOpenFrame;
import interfaces.FTPWindow;
import interfaces.MailWindow;
import mail.MailInbox;

/**
 * La clase MailController se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 09/01/2020
 */

public class MailController {

	private FTPWindow ftpWindow;
	private MailWindow mailWindow;

	public MailController(String user, String pass, MailWindow mailWindow, FTPWindow ftpWindow) {
		this.mailWindow = mailWindow;
		this.ftpWindow = ftpWindow;
		MailInbox mailTools = new MailInbox(user, pass, this.mailWindow.getMessageModel(), this.mailWindow);
		try {
			mailTools.fillInbox();
			this.mailWindow.getBtnWriteMail().addActionListener(new Events.ListenerOpenWriteMessage());
			this.mailWindow.getBtnOpenFTP().addActionListener(new ListenerOpenFrame(ftpWindow,mailWindow));
			mailTools.addListener(this.mailWindow.getListUserMails());
			mailTools.setAutoRefresh();
			this.mailWindow.setVisible(true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

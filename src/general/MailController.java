package general;

import javax.mail.MessagingException;

import ftp.Users;
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

	public MailController(Users user) throws MessagingException {
		MailWindow mailWindow = new MailWindow();
		MailInbox mailTools = new MailInbox(user.geteMail(), user.getPassword(), mailWindow.getMessageModel(),
				mailWindow);
		try {
			mailTools.fillInbox();
			mailWindow.getBtnWriteMail().addActionListener(new Events.ListenerOpenWriteMessage());
			mailTools.addListener(mailWindow.getListUserMails());
			mailWindow.setVisible(true);
			mailTools.refresh();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

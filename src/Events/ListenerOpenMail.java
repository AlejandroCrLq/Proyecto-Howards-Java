package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interfaces.MailWindow;
import interfaces.FTPWindow;

public class ListenerOpenMail implements ActionListener{
	MailWindow MailWindow;
	FTPWindow FtpWindow;
	public ListenerOpenMail(MailWindow MailWindow,FTPWindow FtpWindow) {
		this.MailWindow = MailWindow;
		this.FtpWindow = FtpWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		FtpWindow.setVisible(false);
		MailWindow.setVisible(true);
	}
}
